package news.ta.com.news.feature.hackernewsdetail

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityHackerNewDetailsBinding
import news.ta.com.news.feature.hackernewsdetail.HackerNewsDetailActivity.Companion.EXTRA_NEWS_ID
import news.ta.com.news.feature.hackernewslist.HackerNewsListRouter
import news.ta.com.news.feature.hackernewslist.HackerNewsListRouterImpl
import news.ta.com.news.feature.hackernewslist.HackerNewsViewModel

class HackerNewsActivityBinder(activity: AppCompatActivity, val binding: ActivityHackerNewDetailsBinding?) {


    val id = activity.intent.getIntExtra(EXTRA_NEWS_ID, 0)

    val viewModel = ViewModelProviders.of(activity, HackerNewsDetailViewModelFactory(id))
            .get(HackerNewsDetailViewModel::class.java)

    val shareViewModel = ViewModelProviders.of(activity).get(HackerNewsViewModel::class.java)

    val router: HackerNewsListRouter = HackerNewsListRouterImpl(activity.baseContext)

    val view: HackerNewsDetailsView = HackerNewsDetailsViewImpl(binding, shareViewModel, activity)

    fun bindTo(owner: LifecycleOwner) {
        binding?.viewModel = viewModel
        viewModel.itemMediator.observe(owner, Observer { view.setItem(it?.list) })
        shareViewModel.itemClickEvent.observe(owner, Observer { it?.let { router.gotoDetail(it.id) } })
    }
}