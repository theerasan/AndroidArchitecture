package news.ta.com.news.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun FragmentManager.replaceWith(id: Int?, fragment: Fragment, tag: String? = null) {
    if (id == null) return
    this.beginTransaction().replace(id, fragment, tag).setTransition(android.R.anim.fade_in).commit()
}
