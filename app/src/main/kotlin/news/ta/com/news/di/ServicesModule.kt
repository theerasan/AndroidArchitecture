package news.ta.com.news.di

import dagger.Module
import dagger.Provides
import news.ta.com.news.services.NewsService
import retrofit2.Retrofit

@Module
open class ServicesModule {
    @Provides
    open fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)
}