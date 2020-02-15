package news.ta.com.news.feature.newslist

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBe
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.amshove.kluent.mock
import org.junit.rules.TestRule
import org.junit.Rule

class NewsListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val repository: NewsRepository = mock()

    @Test
    fun `case-01 init viewModel - repository should be auto injected`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.repository shouldNotBe null
    }

    @Test
    fun `case-02 init viewModel - default state of hasDetail should be false`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.hasDetailView shouldBe false
    }

    @Test
    fun `case-03 init viewModel - default items should equal to null`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.items shouldEqual null
    }

    @Test
    fun `case-04 init viewModel - items showDetailMediator not has observe`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.showDetailMediator.hasObservers() shouldBe false
    }

    @Test
    fun `case-05 init viewModel - items gotoDetailMediator not has observe`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.gotoDetailMediator.hasObservers() shouldBe false
    }

    @Test(expected = NullPointerException::class)
    fun `case-06 init viewModel - items has null observe`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.items.hasObservers() shouldBe false
    }

    @Test
    fun `case-07 viewModel observe for itemClickEvent when hasDetailView = false, gotoDetailMediator should = item`() {
        val viewModel = NewsListViewModel(repository)
        val newsItem = NewsItem()
        viewModel.hasDetailView = false
        viewModel.gotoDetailMediator.observeForever {
            it shouldEqual newsItem
        }
    }

    @Test
    fun `case-08 viewModel observe for itemClickEvent when hasDetailView = true, showDetailMediator should = item`() {
        val viewModel = NewsListViewModel(repository)
        val newsItem = NewsItem()
        viewModel.hasDetailView = true
        viewModel.showDetailMediator.observeForever {
            it shouldEqual newsItem
        }
    }

    @Test
    fun `case-09 viewModel call afterGotoDetail, gotoDetailMediator should = null`() {
        val viewModel = NewsListViewModel(repository)
        viewModel.afterGotoDetail()
        viewModel.gotoDetailMediator.observeForever {
            it shouldBe null
        }
    }
}