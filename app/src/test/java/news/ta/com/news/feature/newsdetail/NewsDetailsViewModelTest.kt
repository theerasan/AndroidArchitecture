package news.ta.com.news.feature.newsdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import news.ta.com.news.feature.newslist.NewsItem
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBe
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class NewsDetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `case-01 when init NewsDetailsViewModel blankContent = true`() {
        val viewModel = NewsDetailsViewModel()
        viewModel.blankContent.get() shouldBe true
    }

    @Test
    fun `case-02 when init NewsDetailsViewModel onClickReadMore not null`() {
        val viewModel = NewsDetailsViewModel()
        viewModel.onClickReadMore shouldNotBe null
    }

    @Test
    fun `case-03 init NewsDetailsViewModel the set item blankContent != true and selectedEvent = true`() {
        val viewModel = NewsDetailsViewModel()
        viewModel.selectedEvent.observeForever { it shouldBe true }
        viewModel.item.set(NewsItem())
        viewModel.blankContent.get() shouldBe false
    }

    @Test
    fun `case-04 init NewsDetailsViewModel the set item perfrom onClick clickReadMoreEvent should change data`() {
        val viewModel = NewsDetailsViewModel()
        val newsItem = NewsItem(link = Math.random().toString())
        viewModel.clickReadMoreEvent.observeForever { it shouldEqual newsItem.link }
        viewModel.item.set(newsItem)
        viewModel.onClickReadMore.onClick(mock())
    }

    @Test
    fun `case-05 init NewsDetailsViewModel the not set item  and perfrom onClick clickReadMoreEvent = empty`() {
        val viewModel = NewsDetailsViewModel()
        viewModel.clickReadMoreEvent.observeForever { it shouldEqual "" }
        viewModel.onClickReadMore.onClick(mock())
    }
}