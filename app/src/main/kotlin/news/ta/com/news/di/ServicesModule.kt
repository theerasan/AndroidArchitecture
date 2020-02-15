package news.ta.com.news.di

import news.ta.com.news.services.NewsService
import org.koin.dsl.module
import retrofit2.Retrofit

class ServicesModule {

    fun getModule() = module {
        single { provideNewsService(get()) }
    }

    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)
}