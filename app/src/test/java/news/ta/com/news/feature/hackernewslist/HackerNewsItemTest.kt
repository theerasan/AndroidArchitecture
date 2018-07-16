package news.ta.com.news.feature.hackernewslist

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal to`
import org.amshove.kluent.mock
import org.junit.Test

class HackerNewsItemTest {

    @Test
    fun initHackerNewsItemGetInitialData() {
        val hackerNewsItem = HackerNewsItem()

        hackerNewsItem.id `should equal to` 0
        hackerNewsItem.commentCount!! `should equal to` 0
        hackerNewsItem.title!! `should equal to` ""
        hackerNewsItem.text!! `should equal to` ""
        hackerNewsItem.by!! `should equal to` ""
        hackerNewsItem.list!!.isEmpty() `should be` true
    }

    @Test
    fun initHackerNewsItemGetWithData() {

        val mockInt = 3
        val mockString = "String"
        val mockList = mock<List<HackerNewsItem>>()
        val hackerNewsItem = HackerNewsItem(mockInt, mockInt, mockString, mockString, mockString, mockList)

        hackerNewsItem.id `should equal to` mockInt
        hackerNewsItem.commentCount!! `should equal to` mockInt
        hackerNewsItem.title!! `should equal to` mockString
        hackerNewsItem.text!! `should equal to` mockString
        hackerNewsItem.by!! `should equal to` mockString
        hackerNewsItem.list!! `should be` mockList
    }
}