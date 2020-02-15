package news.ta.com.news.feature.newsdetail

import android.app.Activity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import news.ta.com.news.databinding.ActivityNewsDetailsBinding
import news.ta.com.news.databinding.FragmentNewsDetailsBinding
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class NewsDetailModule {
    fun getModule(activity: FragmentActivity, binding: FragmentNewsDetailsBinding?) = module {
        scope(named<NewsDetailFragment>()){
            factory { NewsDetailBinder(binding,get(), get()) }
            factory<NewsDetailRouter> { NewsDetailRouterImpl(activity) }
            viewModel {
                val viewModel: NewsDetailsViewModel by activity.viewModels()
                viewModel
            }
        }
    }

    fun getModule(activity: AppCompatActivity, binding: ActivityNewsDetailsBinding?) = module {
        scope(named<NewsDetailActivity>()){
            factory { NewsDetailActivityBinder(activity, binding, get()) }
            viewModel {
                val viewModel: NewsDetailsViewModel by activity.viewModels()
                viewModel
            }
        }
    }
}