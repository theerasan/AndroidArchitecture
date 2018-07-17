package news.ta.com.news.feature.hackernewslist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.validateMockitoUsage
import com.nhaarman.mockito_kotlin.verify
import news.ta.com.news.feature.TestEnvironmentBuilder
import news.ta.com.news.model.HackerNewsDTO
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class HackerNewsRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun `init applicationComponent`() {
        TestEnvironmentBuilder.buildEnvironment()
    }

    @Test
    fun `case 1 - call getNews expect getHackerNewsList`() {
        val repo = HackerNewsRepositoryImpl()
        val service = repo.service
        Mockito.`when`(service.getHackerNewsList()).thenReturn(mock())

        repo.getNews()
        verify(service).getHackerNewsList()
    }

    @Test
    fun `case 2 - call getNewDetail expect getNewsDetail with same id`() {
        val repo = HackerNewsRepositoryImpl()
        val service = repo.service
        val id = Math.random().toInt()

        Mockito.`when`(service.getHackerNewsDetail(id)).thenReturn(mock())

        repo.getNewsDetail(id)
        verify(service).getHackerNewsDetail(id)
    }

    @Test
    fun `case 3 - Convert NewsDTO with no data to NewItem`() {
        val dto = HackerNewsDTO()
        val newsItem = dto.convertToHackerNewsItem()

        newsItem.id `should be` 0
        newsItem.list `should be` null
        newsItem.text `should be` null
        newsItem.commentCount `should equal` 0
        newsItem.title `should equal` ""
        newsItem.by `should equal` null
    }

    @Test
    fun `case 4 - Convert NewsDTO with data to NewItem`() {
        val dto = HackerNewsDTO()
        dto.by = "string"
        dto.id = Math.random().toInt()
        dto.kids = emptyList()
        dto.text = "string"
        dto.time = 100
        dto.type = "string"
        dto.comment = 12
        dto.title = "string"
        val newsItem = dto.convertToHackerNewsItem()

        newsItem.id `should equal` dto.id
        newsItem.list `should equal` dto.kids
        newsItem.text `should equal` dto.text
        newsItem.commentCount `should equal` dto.comment
        newsItem.title `should equal` dto.title
        newsItem.by `should equal` dto.by
    }

    @Test
    fun `case 5 - Convert id NewItem`() {
        val id = Math.random().toInt()
        val newsItem = id.convertToHackerNewsItem()

        newsItem.id `should equal` id
        newsItem.commentCount `should equal` 0
        newsItem.by `should equal` ""
        newsItem.title `should equal` ""
        newsItem.text `should equal` ""
        newsItem.list `should equal` emptyList()
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}