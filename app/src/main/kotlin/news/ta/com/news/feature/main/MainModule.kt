package news.ta.com.news.feature.main

import news.ta.com.news.databinding.ActivityMainBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainModule {
    fun getModule(activity: MainActivity, binding: ActivityMainBinding) = module {
        scope(named<MainActivity>()) {
            factory { MainBinder(get(), get()) }
            factory<MainView> { MainViewImpl(binding, activity.supportFragmentManager) }
            viewModel { NewsDetailsViewModel() }
        }
    }
}