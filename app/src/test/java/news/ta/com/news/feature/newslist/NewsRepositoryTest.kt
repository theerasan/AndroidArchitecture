package news.ta.com.news.feature.newslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.verify
import news.ta.com.news.model.ArticleDTO
import news.ta.com.news.model.NewsDTO
import news.ta.com.news.services.NewsService
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class NewsRepositoryTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    var service: NewsService = mock()

    @Test
    fun `case-01 init repository with service`() {
        val repository = NewsRepositoryImpl(service)
        repository.service shouldNotBe null
    }

    @Test
    fun `case-02 when repository call getNews, service should call getTopNews with 'us'`() {
        val repository = NewsRepositoryImpl(service)
        Mockito.`when`(repository.service.getTopNewsList("us")).thenReturn(mock())
        repository.getNews()
        verify(repository.service.getTopNewsList("us"))
    }

    @Test
    fun `case-03 convert NewsDTO with article to get List of newsItem`() {
        val newsDTO = NewsDTO()
        val list = ArrayList<ArticleDTO>()
        list.add(ArticleDTO())
        list.add(ArticleDTO())
        newsDTO.articles = list
        val item = newsDTO.articles.convertToNewsItem()

        item.size shouldEqualTo list.size
    }

    @Test
    fun `case-04 convert NewsDTO with null article to get List of newsItem`() {
        val newsDTO = NewsDTO()
        newsDTO.articles = null
        val item = newsDTO.articles?.convertToNewsItem()

        item shouldBe null
    }

    @Test
    fun `case-5 create NewsItem with input nothing get default data`() {
        val item = NewsItem()

        with(item) {
            id shouldEqualTo 0
            thumbnail shouldEqual ""
            link shouldEqual ""
            headline shouldEqual "--"
            description shouldEqual "--"
            source shouldEqual "--"
        }
    }

    @Test
    fun `case-6 create NewsItem with input constructor get same data`() {
        val id = Math.random().toInt()
        val string = Math.random().toString()

        val item = NewsItem(id = id,
                thumbnail = string,
                link = string,
                headline = string,
                description = string,
                source = string)

        with(item) {
            id shouldEqualTo id
            thumbnail shouldEqual string
            link shouldEqual string
            headline shouldEqual string
            description shouldEqual string
            source shouldEqual string
        }
    }
}