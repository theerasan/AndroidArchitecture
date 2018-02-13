package news.ta.com.news.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SourceDTO {

    @SerializedName("id")
    @Expose
    var id: Any? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}
