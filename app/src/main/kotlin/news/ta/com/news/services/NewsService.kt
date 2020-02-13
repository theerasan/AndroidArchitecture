package news.ta.com.news.services

import news.ta.com.news.model.NewsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun getTopNewsList(@Query("country") country: String? = "th"): Call<NewsDTO>
}