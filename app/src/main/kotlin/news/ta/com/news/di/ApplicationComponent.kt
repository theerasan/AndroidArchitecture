package news.ta.com.news.di

import com.google.gson.Gson
import dagger.Component
import news.ta.com.news.feature.hackernewslist.HackerNewsViewModel
import news.ta.com.news.feature.newslist.NewsListViewModel
import news.ta.com.news.services.HackerNewsService
import news.ta.com.news.services.NewsService
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ServicesModule::class, ContextModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getNewsService(): NewsService

    fun getHackerNewsService(): HackerNewsService

    fun getGson(): Gson

    fun inject(newsListViewModel: NewsListViewModel)
    fun inject(newsListViewModel: HackerNewsViewModel)
}