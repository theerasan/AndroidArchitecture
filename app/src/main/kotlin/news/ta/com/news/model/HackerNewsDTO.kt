package news.ta.com.news.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HackerNewsDTO {

    @SerializedName("by")
    @Expose
    var by: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("kids")
    @Expose
    var kids: List<Int>? = null
    @SerializedName("parent")
    @Expose
    var parent: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("time")
    @Expose
    var time: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("descendants")
    @Expose
    var comment: Int? = 0
    @SerializedName("title")
    @Expose
    var title: String? = ""
}