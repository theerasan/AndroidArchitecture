package news.ta.com.news.feature.hackernewdetail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.validateMockitoUsage
import com.nhaarman.mockito_kotlin.verify
import news.ta.com.news.feature.TestEnvironmentBuilder
import news.ta.com.news.feature.hackernewsdetail.HackerNewsDetailViewModel
import news.ta.com.news.feature.hackernewslist.HackerNewsItem
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class HackerNewsDetailViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun `init applicationComponent`() {
        TestEnvironmentBuilder.buildEnvironment()
    }

    @Test
    fun `case-1 call get itemData repository was call getNewsDetail`() {
        val id = Math.random().toInt()
        val vm = HackerNewsDetailViewModel(id)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(id)).thenReturn(liveData)

        verify(vm.repository).getNewsDetail(id)
    }

    @Test
    fun `case-2 call get itemData repository return same item`() {
        val id = Math.random().toInt()
        val vm = HackerNewsDetailViewModel(id)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(id)).thenReturn(liveData)

        vm.itemData `should equal` liveData
    }

    @Test
    fun `case-3 on get data item was set`() {
        val id = Math.random().toInt()
        val vm = HackerNewsDetailViewModel(id)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(id)).thenReturn(liveData)
        val hackerNewsItem = HackerNewsItem()
        vm.onGetData(hackerNewsItem)

        vm.item.get() `should equal` hackerNewsItem
        vm.itemMediator.value `should equal` hackerNewsItem
    }

    @Test
    fun `case-4 on get data when data has comment show same number`() {
        val id = Math.random().toInt()
        val vm = HackerNewsDetailViewModel(id)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(id)).thenReturn(liveData)
        val comment = Math.random().toInt()
        val list = mock<List<HackerNewsItem>>()

        Mockito.`when`(list.size).thenReturn(comment + 1)
        val hackerNewsItem = HackerNewsItem(commentCount = comment, list = list)
        vm.onGetData(hackerNewsItem)

        vm.item.get()!!.commentCount `should equal` hackerNewsItem.commentCount
        vm.item.get()!!.list `should equal` hackerNewsItem.list
    }

    @Test
    fun `case-5 on get data when data has no comment show same number of child`() {
        val id = Math.random().toInt()
        val vm = HackerNewsDetailViewModel(id)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(id)).thenReturn(liveData)
        val comment = 0
        val list = mock<List<HackerNewsItem>>()

        Mockito.`when`(list.size).thenReturn(comment + 1)
        val hackerNewsItem = HackerNewsItem(commentCount = comment, list = list)
        vm.onGetData(hackerNewsItem)

        vm.item.get()!!.commentCount `should equal` hackerNewsItem.list!!.size
        vm.item.get()!!.list `should equal` hackerNewsItem.list
    }

    @Test
    fun `case-6 all variable not null when init`() {
        val id = Math.random().toInt()
        val vm = HackerNewsDetailViewModel(id)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(id)).thenReturn(liveData)

        vm.repository `should not be` null
        vm.itemData `should not be` null
        vm.itemMediator `should not be` null
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}