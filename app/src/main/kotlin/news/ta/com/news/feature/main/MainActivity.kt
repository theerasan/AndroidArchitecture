package news.ta.com.news.feature.main

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.R
import news.ta.com.news.databinding.ActivityMainBinding
import org.koin.android.scope.currentScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class MainActivity : AppCompatActivity() {

    var module: Module? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        Log.d("ROTATE", "Create")
        module = MainModule().getModule(this, binding)
        module?.let {
            loadKoinModules(it)
        }
        val binder: MainBinder = currentScope.get()
        binder.bindTo(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        module?.let {
            unloadKoinModules(it)
        }
    }
}
