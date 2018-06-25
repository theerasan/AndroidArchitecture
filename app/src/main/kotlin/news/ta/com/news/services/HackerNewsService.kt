package news.ta.com.news.services

import news.ta.com.news.model.HackerNewsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    @GET("v0/askstories.json?print=pretty")
    fun getHackerNewsList(): Call<List<Int>>

    @GET("v0/item/{topicId}.json")
    fun getHackerNewsDetail(@Path("topicId") topicId: Int? = 0): Call<HackerNewsDTO>
}