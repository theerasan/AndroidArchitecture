package news.ta.com.news.feature

import androidx.multidex.MultiDexApplication
import news.ta.com.news.di.ApplicationComponent
import news.ta.com.news.di.ContextModule
import news.ta.com.news.di.DaggerApplicationComponent
import news.ta.com.news.di.NetworkModule
import news.ta.com.news.di.RepositoryModule
import news.ta.com.news.di.ServicesModule
import news.ta.com.news.feature.newslist.NewsItem

class NewsApplication : MultiDexApplication() {

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent

        var news: NewsItem? = null
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(ContextModule(applicationContext))
                .networkModule(NetworkModule())
                .servicesModule(ServicesModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}