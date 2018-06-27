package news.ta.com.news.feature.newsdetail

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.view.View
import news.ta.com.news.common.livedata.SingleLiveEvent
import news.ta.com.news.common.onValueChange
import news.ta.com.news.feature.newslist.NewsItem

class NewsDetailsViewModel : ViewModel() {
    val item = ObservableField<NewsItem?>()
    val clickReadMoreEvent = SingleLiveEvent<String>()
    val onClickReadMore = View.OnClickListener { clickReadMoreEvent.value = item.get()?.link ?: "" }
    val selectedEvent = SingleLiveEvent<Boolean>()
    val blankContent = ObservableField<Boolean>(true)

    init {
        item.onValueChange { selectedEvent.value = true; blankContent.set(false) }
    }
}