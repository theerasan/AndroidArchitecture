package news.ta.com.news.feature.hackernewslist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.ta.com.news.R
import news.ta.com.news.databinding.FragmentHackerNewsListBinding

class HackerNewListFragment : Fragment() {

    private lateinit var binder: HackerNewsListBinder

    companion object {
        fun newInstance() = HackerNewListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentHackerNewsListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hacker_news_list, container, false)
        binder = HackerNewsListBinder(this, binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.bindTo(this)
    }
}