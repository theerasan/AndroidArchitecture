package news.ta.com.news.di

import news.ta.com.news.feature.hackernewslist.HackerNewsRepository
import org.amshove.kluent.mock

open class TestRepositoryModule : RepositoryModule() {
    override fun provideHackerNewsRepository(): HackerNewsRepository = mock()
}
