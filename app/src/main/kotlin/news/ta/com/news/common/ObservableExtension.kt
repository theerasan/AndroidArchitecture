package news.ta.com.news.common

import android.databinding.Observable
import android.databinding.ObservableField

fun <T> ObservableField<T>.onValueChange(callback: (T) -> Unit) {
    val that = this
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            that.get()?.let { callback.invoke(it) }
        }
    })
}
