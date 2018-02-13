package news.ta.com.news.feature.main

import android.support.v4.widget.NestedScrollView
import news.ta.com.news.R
import news.ta.com.news.databinding.ActivityMainBinding

interface MainView {
    fun scrollDetailToTop()
}

class MainViewImpl(val binding: ActivityMainBinding?) : MainView {
    override fun scrollDetailToTop() {
        val scrollView = binding?.detail?.findViewById<NestedScrollView>(R.id.scrollContainer)
        scrollView?.scrollTo(0, 0)
    }
}