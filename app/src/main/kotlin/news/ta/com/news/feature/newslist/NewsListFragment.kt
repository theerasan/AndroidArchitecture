package news.ta.com.news.feature.newslist

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.ta.com.news.R
import news.ta.com.news.databinding.FragmentNewsListBinding
import org.koin.android.scope.currentScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class NewsListFragment : Fragment() {

    private lateinit var binder: NewsListBinder
    var module: Module? = null

    companion object {

        val HAS_DETAIL = "news.ta.com.news.feature.newslist.HAS_DETAIL"

        fun newInstance(hasDetail: Boolean): Fragment {
            val fragment = NewsListFragment()
            val bundle = Bundle()
            bundle.putSerializable(HAS_DETAIL, hasDetail)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentNewsListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false)
        module = NewsListModule().getModule(this, binding)
        module?.let {
            unloadKoinModules(it)
            loadKoinModules(it)
        }
        binder = currentScope.get()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.bindTo(this)
    }
}