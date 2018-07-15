package news.ta.com.news.feature.main

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityMainBinding

class MainBinder(activity: AppCompatActivity, binding: ActivityMainBinding?) {

    private val router: MainRouter = MainRouterImpl(activity.supportFragmentManager, binding)

    init {
        router.showList()
    }

    fun bindTo(owner: LifecycleOwner) {
    }
}