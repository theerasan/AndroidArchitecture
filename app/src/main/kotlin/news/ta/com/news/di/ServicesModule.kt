package news.ta.com.news.di

import dagger.Module
import dagger.Provides
import news.ta.com.news.services.HackerNewsService
import retrofit2.Retrofit

@Module
open class ServicesModule {
    @Provides
    open fun provideHackerNewsService(retrofit: Retrofit): HackerNewsService = retrofit.create(HackerNewsService::class.java)
}