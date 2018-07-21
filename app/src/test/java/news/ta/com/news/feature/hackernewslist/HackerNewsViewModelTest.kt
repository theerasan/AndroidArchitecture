package news.ta.com.news.feature.hackernewslist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import com.nhaarman.mockito_kotlin.atLeast
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.validateMockitoUsage
import com.nhaarman.mockito_kotlin.verify
import news.ta.com.news.feature.TestEnvironmentBuilder
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class HackerNewsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun `init applicationComponent`() {
        TestEnvironmentBuilder.buildEnvironment()
    }

    @Test
    fun `case-1 get call get items repository was call getNews`() {
        val vm = HackerNewsViewModel()
        val liveData = mock<LiveData<List<HackerNewsItem>>>()
        Mockito.`when`(vm.repository.getNews()).thenReturn(liveData)

        verify(vm.repository).getNews()
    }

    @Test
    fun `case-2 get call get items repository return same item`() {
        val vm = HackerNewsViewModel()
        val liveData = mock<LiveData<List<HackerNewsItem>>>()
        Mockito.`when`(vm.repository.getNews()).thenReturn(liveData)
        vm.items `should equal` liveData
    }

    @Test
    fun `case-3 Do refresh then refreshing state should active and call getNews`() {
        val vm = HackerNewsViewModel()
        val liveData = mock<LiveData<List<HackerNewsItem>>>()
        Mockito.`when`(vm.repository.getNews()).thenReturn(liveData)

        vm.onSwipeRefreshLayout.onRefresh()

        vm.refreshing.get() `should equal` true
        val repository = vm.repository
        verify(repository, atLeast(2)).getNews()
    }

    @Test
    fun `case-4 Do get new list data after refreshing refresh is gone`() {
        val vm = HackerNewsViewModel()
        vm.refreshing.set(true)
        val data = mock<List<HackerNewsItem>>()
        vm.onGetNewData(data)
        vm.refreshing.get() `should equal` false
    }

    @Test
    fun `case-5 Do get null list data after refreshing refresh still show`() {
        val vm = HackerNewsViewModel()
        vm.refreshing.set(true)
        vm.onGetNewData(null)
        vm.refreshing.get() `should equal` true
    }

    @Test
    fun `case-6 initial variable are not null`() {
        val vm = HackerNewsViewModel()
        val liveData = mock<LiveData<List<HackerNewsItem>>>()
        Mockito.`when`(vm.repository.getNews()).thenReturn(liveData)

        vm.items `should not be` null
        vm.itemMediator `should not be` null
        vm.repository `should not be` null
        vm.refreshing `should not be` null
        vm.onSwipeRefreshLayout `should not be` null
        vm.itemClickEvent `should not be` null
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}