package news.ta.com.news.feature.hackernewsdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class HackerNewsDetailViewModelFactory(val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HackerNewsDetailViewModel::class.java)) {
            return HackerNewsDetailViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}