package news.ta.com.news.common

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun bindImageToImageView(view: ImageView, url: String?) {
    when (url) {
        is String -> {
            Glide.with(view.context)
                    .load(url)
                    .into(view)
        }
    }
}

@BindingAdapter("show")
fun showOrHideView(view: View, show: Boolean) {
    when (show) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}