package news.ta.com.news.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.replaceWith(id: Int?, fragment: Fragment, tag: String? = null) {
    if (id == null) return
    this.beginTransaction().replace(id, fragment, tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
}
