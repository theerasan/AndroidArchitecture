package news.ta.com.news.common

import android.databinding.BindingAdapter
import android.support.annotation.PluralsRes
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("htmlString")
fun setHtmlStringToTextView(view: TextView, htmlString: String?) {
    htmlString?.let {
        view.text = htmlString.parseHtml()
    }
}

@BindingAdapter("rawValue", "stringFormatRes")
fun setDiscount(view: TextView, rawValue: Int, @PluralsRes stringFormatRes: Int) {
    with(view) {
        text = context.resources.getQuantityString(stringFormatRes, rawValue, rawValue)
    }
}

@BindingAdapter("onRefresh", "isRefreshing")
fun bindSwipeEvent(view: SwipeRefreshLayout, onRefresh: SwipeRefreshLayout.OnRefreshListener?, isRefreshing: Boolean) {
    view.setOnRefreshListener(onRefresh)
    view.isRefreshing = isRefreshing
}