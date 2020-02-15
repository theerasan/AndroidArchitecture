package news.ta.com.news.feature.newslist

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class NewsListModule {
    fun getModule(fragment: Fragment, binding: FragmentNewsListBinding): Module = module {
        scope(named<NewsListFragment>()) {
            factory { binding }
            factory { NewsListBinder(fragment, binding, get(), get(), get()) }
            viewModel {
                val viewModel: NewsDetailsViewModel by fragment.activity!!.viewModels()
                viewModel
            }
            viewModel { NewsListViewModel(get()) }
            factory<NewsListRouter> { NewsListRouterImpl(fragment.context!!) }
        }
    }

}