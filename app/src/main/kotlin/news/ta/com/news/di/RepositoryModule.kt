package news.ta.com.news.di

import news.ta.com.news.feature.newslist.NewsRepository
import news.ta.com.news.feature.newslist.NewsRepositoryImpl
import org.koin.dsl.module


open class RepositoryModule {

    fun getModule() = module {
        single<NewsRepository> { NewsRepositoryImpl(get()) }
    }


}
