package news.ta.com.news.services

import android.arch.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import news.ta.com.news.common.coroutines.Android
import news.ta.com.news.feature.NewsApplication
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class ErrorBody(
        @SerializedName("errorType")
        val errorType: String,
        @SerializedName("errors")
        val errors: List<ErrorItem>
)

data class ErrorItem(
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String
)

class DataTransferCallback<T>(private val success: (T?) -> Unit,
                              private val successHeaders: ((Headers?) -> Unit)? = null,
                              private val fail: ((ResponseType, Throwable?) -> Unit)?) : Callback<T> {

    private val gson = NewsApplication.applicationComponent.getGson()

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        when (response?.code()) {
            in 200..399 -> {
                success.invoke(response?.body())
                successHeaders?.invoke(response?.headers())
            }
            401 -> fail?.invoke(ResponseType.UNAUTHORIZED, null)
            503 -> fail?.invoke(ResponseType.EMPTY, null)
            504 -> fail?.invoke(ResponseType.TIMEOUT, null)
            404 -> fail?.invoke(ResponseType.NOT_FOUND, null)

            else -> {
                val errorResponse = response?.errorBody()?.string()
                val errorBody: ErrorBody = gson.fromJson(errorResponse, ErrorBody::class.java)
                with(errorBody) {
                    val throwable = Throwable(errorType, Throwable(errors[0].message))
                    fail?.invoke(ResponseType.GENERAL_ERROR, throwable)
                }
            }
        }
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

fun <T, O> Call<T>.enqueueWithProcessing(
        preProcessing: (T?) -> O,
        success: (O?) -> Unit,
        successHeaders: ((Headers?) -> Unit)? = null,
        fail: (ResponseType, Throwable?) -> Unit) {

    fun backgroundProcessing(obj: T?): Deferred<O> {
        return async(CommonPool) {
            return@async preProcessing(obj)
        }
    }

    val wrappedSuccess: (T?) -> Unit = {
        launch(Android) {
            val obj = backgroundProcessing(it).await()
            success.invoke(obj)
        }
    }

    this.enqueue(DataTransferCallback<T>(wrappedSuccess, successHeaders, fail))
}

fun <T> MutableLiveData<T>.setValueAsync(background: (T?) -> T): Job {
    fun doInBackground(): Deferred<T> {
        return async(CommonPool) {
            background.invoke(this@setValueAsync.value)
        }
    }

    val job = launch(Android) {
        this@setValueAsync.value = doInBackground().await()
    }

    return job
}

