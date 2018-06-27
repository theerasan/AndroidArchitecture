package news.ta.com.news.common.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations

fun <X, Y> LiveData<X>.map(func: (source: X) -> Y) = Transformations.map(this, func)

fun <X, Y> LiveData<X>.switchMap(func: (source: X?) -> LiveData<Y>) = Transformations.switchMap(this, func)

fun <X, Y, Z> LiveData<X>.zip(stream: LiveData<Y>, func: (source1: X?, source2: Y?) -> Z): LiveData<Z> {
    val result = MediatorLiveData<Z>()
    result.addSource(this, { x -> result.setValue(func.invoke(x, stream.value)) })
    result.addSource(stream, { y -> result.setValue(func.invoke(this.value, y)) })
    return result
}

fun <X> LiveData<X>.merge(stream: LiveData<X>): LiveData<X> {
    val result = MediatorLiveData<X>()
    result.addSource(this, { x -> result.setValue(x) })
    result.addSource(stream, { x -> result.setValue(x) })
    return result
}

fun <X, Y> LiveData<X>.mapNotNull(func: (source: X?) -> Y?): LiveData<Y> {
    val result = MediatorLiveData<Y>()
    result.addSource(this, { x ->
        val y = func.invoke(x)
        if (y != null) result.setValue(y)
    })
    return result
}

fun <X> LiveData<X>.filter(func: (source: X?) -> Boolean): LiveData<X> {
    val result = MediatorLiveData<X>()
    result.addSource(this, { x -> if (func.invoke(x)) result.setValue(x) })
    return result
}

fun <X> LiveData<X>.filterNotNull(func: (source: X) -> Boolean): LiveData<X> {
    val result = MediatorLiveData<X>()
    result.addSource(this, { x ->
        if (x != null && func.invoke(x)) result.setValue(x)
    })
    return result
}

fun <X> LiveData<X?>.filterOutNull(): LiveData<X> {
    val result = MediatorLiveData<X>()
    result.addSource(this, { x ->
        if (x != null) result.setValue(x)
    })
    return result
}

fun <M, S> MediatorLiveData<M>.observeOnce(source: LiveData<S>, func: (data: S?) -> Unit) {
    this.removeSource(source)
    this.addSource(source, {
        func.invoke(it)
        this@observeOnce.removeSource(source)
    })
}