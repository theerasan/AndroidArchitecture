package news.ta.com.news.feature.newslist

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBe
import org.junit.Before
import org.junit.Test
import android.arch.core.executor.testing.InstantTaskExecutorRule
import news.ta.com.news.feature.TestEnvironmentBuilder
import org.junit.rules.TestRule
import org.junit.Rule

class NewsListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun `init applicationComponent`() {
        TestEnvironmentBuilder.buildEnvironment()
    }

    @Test
    fun `case-01 init viewModel - repository should be auto injected`() {
        val viewModel = NewsListViewModel()
        viewModel.repository shouldNotBe null
    }

    @Test
    fun `case-02 init viewModel - default state of haseDetail shoulb be false`() {
        val viewModel = NewsListViewModel()
        viewModel.hasDetailView shouldBe false
    }

    @Test
    fun `case-03 init viewModel - default items should equle to repository items`() {
        val viewModel = NewsListViewModel()
        viewModel.items shouldEqual TestEnvironmentBuilder.repositories.provideNewsRepository().getNews()
    }

    @Test
    fun `case-04 init viewModel - items showDetailMediator not has observe`() {
        val viewModel = NewsListViewModel()
        viewModel.showDetailMediator.hasObservers() shouldBe false
    }

    @Test
    fun `case-05 init viewModel - items gotoDetailMediator not has observe`() {
        val viewModel = NewsListViewModel()
        viewModel.gotoDetailMediator.hasObservers() shouldBe false
    }

    @Test(expected = NullPointerException::class)
    fun `case-06 init viewModel - items has null observe`() {
        val viewModel = NewsListViewModel()
        viewModel.items.hasObservers()
    }

    @Test
    fun `case-07 viewModel observe for itemClickEvent when hasDetailView = false, gotoDetailMediator should = item`() {
        val viewModel = NewsListViewModel()
        val newsItem = NewsItem()
        viewModel.itemClickEvent.value = newsItem
        viewModel.gotoDetailMediator.observeForever {
            it shouldEqual newsItem
        }
    }

    @Test
    fun `case-08 viewModel observe for itemClickEvent when hasDetailView = true, showDetailMediator should = item`() {
        val viewModel = NewsListViewModel()
        val newsItem = NewsItem()
        viewModel.hasDetailView = true
        viewModel.showDetailMediator.observeForever {
            it shouldEqual newsItem
        }
    }

    @Test
    fun `case-09 viewModel call afterGotoDetail, gotoDetailMediator should = null`() {
        val viewModel = NewsListViewModel()
        viewModel.afterGotoDetail()
        viewModel.gotoDetailMediator.observeForever {
            it shouldBe null
        }
    }
}