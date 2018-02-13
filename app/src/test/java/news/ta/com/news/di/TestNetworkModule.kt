package news.ta.com.news.di

import android.content.Context
import com.google.gson.Gson
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.mock
import retrofit2.Retrofit

class TestNetworkModule : NetworkModule() {

    var mockWebServer: MockWebServer? = null

    override fun provideRetrofit(context: Context, gson: Gson): Retrofit {
        mockWebServer = MockWebServer()

        return Retrofit.Builder()
                .baseUrl(mockWebServer!!.url("/"))
                .build()
    }
    override fun createGson(): Gson = mock()
}