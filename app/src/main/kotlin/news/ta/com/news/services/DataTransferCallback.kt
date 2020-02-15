package news.ta.com.news.services

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import news.ta.com.news.services.DataTransferCallback.Companion.ioScope
import news.ta.com.news.services.DataTransferCallback.Companion.mainScope
import kotlinx.coroutines.*
import news.ta.com.news.feature.NewsApplication
import okhttp3.Headers
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class ErrorBody(
        @SerializedName("error")
        val error: String
)

data class ErrorItem(
        @SerializedName("code")
        val code: String,
        @SerializedName("text")
        val message: String
)

class DataTransferCallback<T>(
        private val success: (T?) -> Unit,
        private val headers: ((Headers?) -> Unit)? = null,
        private val fail: ((ResponseType, Throwable?) -> Unit)?
) : Callback<T>, KoinComponent {

    companion object {
        val ioScope = CoroutineScope(Dispatchers.IO)
        val mainScope = CoroutineScope(Dispatchers.Main)
    }

    private val gson:Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setDateFormat("dd-MM-yyyy")
            .create()

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        headers?.invoke(response?.headers())

        when (response?.code()) {
            in 200..399 -> success.invoke(response?.body())
            401 -> {
                val errorBody = getErrorBody(response)
                val throwable = getThrowable(errorBody)
                fail?.invoke(ResponseType.UNAUTHORIZED, throwable)
            }
            503 -> fail?.invoke(ResponseType.EMPTY, null)
            502, 504 -> fail?.invoke(ResponseType.TIMEOUT, null)
            404 -> {
                val errorBody = getErrorBody(response)
                val throwable = getThrowable(errorBody)
                fail?.invoke(ResponseType.NOT_FOUND, throwable)
            }
            else -> {
                handleError(response)
                fail?.invoke(ResponseType.GENERAL_ERROR, null)
            }
        }
    }

    private fun handleError(response: Response<T>?) {
        try {
            val errorBody = getErrorBody(response)
            val throwable = getThrowable(errorBody)
            fail?.invoke(ResponseType.GENERAL_ERROR, throwable)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            fail?.invoke(ResponseType.NOT_FOUND, null)
        }
    }

    private fun getErrorBody(response: Response<T>?): ErrorBody? {
        return try {
            val errorResponse = response?.errorBody()?.string()
            gson.fromJson(errorResponse, ErrorBody::class.java)
        } catch (e: Exception) {
            null
        }
    }

    private fun getThrowable(errorBody: ErrorBody?): Throwable? {
        val message = errorBody?.error
        return Throwable(message)
    }

    override fun onFailure(call: Call<T>?, throwable: Throwable?) {
        if (throwable == null) {
            fail?.invoke(ResponseType.GENERAL_ERROR, throwable)
            return
        }
        when (throwable::class.java) {
            UnknownHostException::class.java -> fail?.invoke(ResponseType.NO_INTERNET, throwable)
            IOException::class.java -> fail?.invoke(ResponseType.NO_INTERNET, throwable)
            InterruptedIOException::class.java -> fail?.invoke(ResponseType.NO_INTERNET, throwable)
            SocketTimeoutException::class.java -> fail?.invoke(ResponseType.TIMEOUT, throwable)
            else -> fail?.invoke(ResponseType.GENERAL_ERROR, throwable)
        }
    }
}

fun <T> Call<T>.processEnqueue(
        success: (T?) -> Unit,
        headers: ((Headers?) -> Unit)? = null,
        fail: ((ResponseType, Throwable?) -> Unit)? = null
) {
    try {
        enqueue(DataTransferCallback<T>(success, headers, fail))
    } catch (e: IOException) {
    }
}

fun <T> Call<T>.enqueueNow() {
    this.enqueueWithProcessing({}, {})
}

fun <T, O> Call<T>.enqueueWithProcessing(
        preProcessing: (T?) -> O,
        success: (O?) -> Unit,
        headers: ((Headers?) -> Unit)? = null,
        fail: ((ResponseType, Throwable?) -> Unit)? = null
) {

    fun backgroundProcessingAsync(obj: T?): Deferred<O> {
        return ioScope.async {
            return@async preProcessing(obj)
        }
    }

    val wrappedSuccess: (T?) -> Unit = {
        mainScope.launch {
            val obj = backgroundProcessingAsync(it).await()
            success.invoke(obj)
        }
    }

    processEnqueue(wrappedSuccess, headers, fail)
}
