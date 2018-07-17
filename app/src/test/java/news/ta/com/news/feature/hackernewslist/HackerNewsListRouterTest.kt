package news.ta.com.news.feature.hackernewslist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.validateMockitoUsage
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HackerNewsListRouterTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `case 1 - call goto detail then context Start activity`() {
        val context = mock<Context>()
        val router = HackerNewsListRouterImpl(context)
        val id = Math.random().toInt()

        router.gotoDetail(id)

        verify(context).startActivity(any())
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}