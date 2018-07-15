package news.ta.com.news.di

import dagger.Module
import dagger.Provides
import news.ta.com.news.feature.hackernewslist.HackerNewsRepository
import news.ta.com.news.feature.hackernewslist.HackerNewsRepositoryImpl

@Module
open class RepositoryModule {

    @Provides
    open fun provideHackerNewsRepository(): HackerNewsRepository = HackerNewsRepositoryImpl()
}
