package news.ta.com.news.di

import news.ta.com.news.services.HackerNewsService
import org.amshove.kluent.mock
import retrofit2.Retrofit

class TestServicesModule : ServicesModule() {
    override fun provideHackerNewsService(retrofit: Retrofit): HackerNewsService = mock()
}