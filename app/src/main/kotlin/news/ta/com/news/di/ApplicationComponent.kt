package news.ta.com.news.di

import com.google.gson.Gson
import dagger.Component
import news.ta.com.news.feature.hackernewsdetail.HackerNewsDetailViewModel
import news.ta.com.news.feature.hackernewslist.HackerNewsViewModel
import news.ta.com.news.feature.hackernewslist.hackernewsitem.HackerNewsItemViewModel
import news.ta.com.news.services.HackerNewsService
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ServicesModule::class, ContextModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getHackerNewsService(): HackerNewsService
    fun getGson(): Gson

    fun inject(newsListViewModel: HackerNewsViewModel)
    fun inject(hackerNewsItemViewModel: HackerNewsItemViewModel)
    fun inject(hackerNewsDetailViewModel: HackerNewsDetailViewModel)
}