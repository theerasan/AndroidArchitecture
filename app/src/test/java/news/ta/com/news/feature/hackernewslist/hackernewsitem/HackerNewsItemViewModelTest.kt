package news.ta.com.news.feature.hackernewslist.hackernewsitem

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.validateMockitoUsage
import com.nhaarman.mockito_kotlin.verify
import news.ta.com.news.feature.TestEnvironmentBuilder
import news.ta.com.news.feature.hackernewslist.HackerNewsItem
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class HackerNewsItemViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun `init applicationComponent`() {
        TestEnvironmentBuilder.buildEnvironment()
    }

    @Test
    fun `case-1 call get item repository was call getNewsDetail`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        verify(vm.repository).getNewsDetail(position)
    }

    @Test
    fun `case-2 call get item repository return same item`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        vm.item `should equal` liveData
    }

    @Test
    fun `case-3 when create vm as parent return same value`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        val list = mock<List<HackerNewsItem>>()
        Mockito.`when`(list.size).thenReturn(position + 1)
        val item = HackerNewsItem(id = position, commentCount = position - 1, list = list, title = "title")

        vm.onGetData(item)

        vm.topic.get()!!.id `should equal` item.id
        vm.topic.get()!!.commentCount `should equal` item.commentCount
        vm.topic.get()!!.list!!.size `should equal` item.list!!.size
        vm.topic.get()!!.title `should equal` item.title
    }

    @Test
    fun `case-4 call when create vm as child return convert value`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position, true)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        val list = mock<List<HackerNewsItem>>()
        Mockito.`when`(list.size).thenReturn(position + 1)
        val item = HackerNewsItem(id = position, commentCount = position - 1, list = list, text = "text")

        vm.onGetData(item)

        vm.topic.get()!!.id `should equal` item.id
        vm.topic.get()!!.commentCount `should equal` item.list!!.size
        vm.topic.get()!!.list!!.size `should equal` item.list!!.size
        vm.topic.get()!!.title `should equal` item.text
    }

    @Test
    fun `case-5 loading gone when get data when create vm as parent`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        vm.loading.get() `should equal` true
        vm.onGetData(HackerNewsItem())
        vm.loading.get() `should equal` false
    }

    @Test
    fun `case-6 loading show when get null data create vm as parent`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        vm.loading.get() `should equal` true
        vm.onGetData(null)
        vm.loading.get() `should equal` true
    }

    @Test
    fun `case-7 loading gone when get data when create vm as child`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position, true)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        vm.loading.get() `should equal` true
        vm.onGetData(HackerNewsItem())
        vm.loading.get() `should equal` false
    }

    @Test
    fun `case-8 loading show when get null data create vm as child`() {
        val position = Math.random().toInt()
        val vm = HackerNewsItemViewModel(position, true)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(position)).thenReturn(liveData)

        vm.loading.get() `should equal` true
        vm.onGetData(null)
        vm.loading.get() `should equal` true
    }

    @Test
    fun `case-9 initial variable are not null`() {
        val vm = HackerNewsItemViewModel(1)
        val liveData = mock<LiveData<HackerNewsItem>>()
        Mockito.`when`(vm.repository.getNewsDetail(1)).thenReturn(liveData)

        vm.item `should not be` null
        vm.repository `should not be` null
        vm.itemMediator `should not be` null
        vm.topic `should not be` null
        vm.loading `should not be` null
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}