package news.ta.com.news.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun androidx.fragment.app.FragmentManager.replaceWith(id: Int?, fragment: androidx.fragment.app.Fragment, tag: String? = null) {
    if (id == null) return
    this.beginTransaction().replace(id, fragment, tag).setTransition(android.R.anim.fade_in).commit()
}
