package news.ta.com.news.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import news.ta.com.news.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

open class NetworkModule {
    fun getModule() = module {
        single {
            GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .setDateFormat("dd-MM-yyyy")
                    .create()
        }
        single {
            Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(get()))
                    .client(createClient(androidContext()))
                    .build()
        }
    }

    private fun createClient(context: Context): OkHttpClient {
        val cacheDir = File(context.cacheDir, "responses")

        return OkHttpClient.Builder()
                .cache(Cache(cacheDir, 10 * 1024 * 1024)) //10Mb
                .addInterceptor(log(BuildConfig.DEBUG))
                .addInterceptor(addHeaderKey())
                .build()
    }

    private fun log(enabled: Boolean): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (enabled) BODY else NONE
        return logging
    }

    private fun addHeaderKey() = Interceptor { chain ->
        var request = chain.request()

        request = request.newBuilder()
                .header("Authorization", "Bearer " + BuildConfig.NEWS_API_KEY)
                .build()

        chain.proceed(request)
    }
}