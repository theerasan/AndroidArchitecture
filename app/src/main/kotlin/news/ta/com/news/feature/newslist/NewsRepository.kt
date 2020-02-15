package news.ta.com.news.feature.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import news.ta.com.news.feature.NewsApplication
import news.ta.com.news.model.ArticleDTO
import news.ta.com.news.services.NewsService
import news.ta.com.news.services.enqueueWithProcessing

interface NewsRepository {
    fun getNews(): LiveData<List<NewsItem>>
}

class NewsRepositoryImpl(val service: NewsService) : NewsRepository {

    var items = MutableLiveData<List<NewsItem>>()

    override fun getNews(): LiveData<List<NewsItem>> {
        service.getTopNewsList("us").enqueueWithProcessing(
                preProcessing = {
                    it?.articles.convertToNewsItem()
                },
                success = {
                    items.value = it
                },
                fail = { _, _ -> }
        )

        return items
    }
}

fun List<ArticleDTO>?.convertToNewsItem(): List<NewsItem> {
    if (this == null) return emptyList()
    return this.asSequence().map { item ->
        NewsItem(id = item.hashCode(),
                thumbnail = item.urlToImage ?: "",
                headline = item.title ?: "--",
                description = item.description ?: "--",
                link = item.url ?: "",
                source = item.source?.name ?: "--")
    }.toList()
}

