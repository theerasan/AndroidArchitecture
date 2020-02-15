package news.ta.com.news.feature.newsdetail

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.ta.com.news.R
import news.ta.com.news.databinding.FragmentNewsDetailsBinding
import org.koin.android.scope.currentScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class NewsDetailFragment : Fragment() {
    private lateinit var binder: NewsDetailBinder
    companion object {
        fun newInstance(): Fragment = NewsDetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentNewsDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        val module = NewsDetailModule().getModule(this.activity!!, binding)
        unloadKoinModules(module)
        loadKoinModules(module)
        binder = currentScope.get()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.bindTo(this)
    }
}