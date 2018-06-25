package news.ta.com.news.di

import dagger.Module
import dagger.Provides
import news.ta.com.news.feature.hackernewslist.HackerNewsRepository
import news.ta.com.news.feature.hackernewslist.HackerNewsRepositoryImpl
import news.ta.com.news.feature.newslist.NewsRepository
import news.ta.com.news.feature.newslist.NewsRepositoryImpl

@Module
open class RepositoryModule {
    @Provides
    open fun provideNewsRepository(): NewsRepository = NewsRepositoryImpl()

    @Provides
    open fun provideHackerNewsRepository(): HackerNewsRepository = HackerNewsRepositoryImpl()
}
