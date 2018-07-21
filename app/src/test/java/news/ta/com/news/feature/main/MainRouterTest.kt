package news.ta.com.news.feature.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.v4.app.FragmentManager
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.validateMockitoUsage
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainRouterTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `case 1 - call showList never beginTransaction`() {
        val fm = mock<FragmentManager>()
        val router = MainRouterImpl(fm, mock())
        router.showList()
        verify(fm, never()).beginTransaction()
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}