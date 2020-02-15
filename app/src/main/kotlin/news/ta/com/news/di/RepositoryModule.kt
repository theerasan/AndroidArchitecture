package news.ta.com.news.di

import dagger.Module
import dagger.Provides
import news.ta.com.news.feature.newslist.NewsRepository
import news.ta.com.news.feature.newslist.NewsRepositoryImpl
import org.koin.dsl.module

@Module
open class RepositoryModule {

    fun getModule() = module {
        single<NewsRepository> { NewsRepositoryImpl(get()) }
    }

}
