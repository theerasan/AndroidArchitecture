package news.ta.com.news.di

import news.ta.com.news.feature.newslist.NewsRepository
import org.amshove.kluent.mock

open class TestRepositoryModule : RepositoryModule() {
    override fun provideNewsRepository(): NewsRepository = mock()
}
