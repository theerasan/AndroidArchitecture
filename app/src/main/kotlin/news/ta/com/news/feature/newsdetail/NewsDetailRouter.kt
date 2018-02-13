package news.ta.com.news.feature.newsdetail

import android.content.Context
import android.content.Intent
import android.net.Uri

interface NewsDetailRouter {
    fun openWebBrowser(url: String?)
}

class NewsDetailRouterImpl(val context: Context) : NewsDetailRouter {

    override fun openWebBrowser(url: String?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context.startActivity(i)
    }
}