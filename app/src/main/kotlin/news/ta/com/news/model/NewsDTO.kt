package news.ta.com.news.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsDTO {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("articles")
    @Expose
    var articles: List<ArticleDTO>? = null
}
