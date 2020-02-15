package news.ta.com.news.feature

import androidx.multidex.MultiDexApplication
import news.ta.com.news.di.NetworkModule
import news.ta.com.news.di.RepositoryModule
import news.ta.com.news.di.ServicesModule
import news.ta.com.news.feature.newslist.NewsItem
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApplication : MultiDexApplication() {

    companion object {
        var news: NewsItem? = null
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            androidLogger()
            modules(
                    listOf(NetworkModule().getModule(),
                            RepositoryModule().getModule(),
                            ServicesModule().getModule()
                    )
            )
        }
    }
}