package news.ta.com.news.feature.newsdetail

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import news.ta.com.news.databinding.FragmentNewsDetailsBinding

class NewsDetailBinder(activity: FragmentActivity, binding: FragmentNewsDetailsBinding) {

    val viewModel = ViewModelProviders.of(activity).get(NewsDetailsViewModel::class.java)
    val router: NewsDetailRouter = NewsDetailRouterImpl(binding.root.context)

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.clickReadMoreEvent.observe(owner, Observer { router.openWebBrowser(it) })
    }
}