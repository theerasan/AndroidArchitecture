package news.ta.com.news.feature.hackernewslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import news.ta.com.news.feature.NewsApplication
import news.ta.com.news.model.HackerNewsDTO
import news.ta.com.news.services.enqueueWithProcessing

interface HackerNewsRepository {
    fun getNews(): LiveData<List<HackerNewsItem>>
    fun getNewsDetail(id: Int): LiveData<HackerNewsItem>
}

class HackerNewsRepositoryImpl: HackerNewsRepository {

    var service = NewsApplication.applicationComponent.getHackerNewsService()
    private val items = MutableLiveData<List<HackerNewsItem>>()

    override fun getNews(): LiveData<List<HackerNewsItem>> {
        service.getHackerNewsList().enqueueWithProcessing(
                preProcessing = {
                    it?.map { it.convertToHackerNewsItem() }
                },
                success = {
                    items.value = it
                },
                fail = {_, _ ->

                }
        )

        return items
    }

    override fun getNewsDetail(id: Int): LiveData<HackerNewsItem> {
        val items = MutableLiveData<HackerNewsItem>()

        service.getHackerNewsDetail(id).enqueueWithProcessing(
                preProcessing = {
                    it?.convertToHackerNewsItem()
                },
                success = {
                    items.value = it
                },
                fail = {_, _ ->

                }
        )

        return items
    }
}

private fun HackerNewsDTO.convertToHackerNewsItem(): HackerNewsItem {
    return HackerNewsItem(id = this.id ?: 0,
            idTemp = this.id?.toString() ?: "",
            text = this.text ?: "")
}

private fun Int.convertToHackerNewsItem(): HackerNewsItem {
    return HackerNewsItem(id = this,
            idTemp = this.toString())
}

