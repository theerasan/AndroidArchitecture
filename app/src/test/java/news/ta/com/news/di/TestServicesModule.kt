package news.ta.com.news.di

import news.ta.com.news.services.NewsService
import org.amshove.kluent.mock
import retrofit2.Retrofit

class TestServicesModule : ServicesModule() {
    override fun provideNewsService(retrofit: Retrofit): NewsService = mock()
}