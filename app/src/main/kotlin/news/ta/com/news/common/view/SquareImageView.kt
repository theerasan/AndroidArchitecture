package news.ta.com.news.common.view

import android.content.Context
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet

class SquareImageView : AppCompatImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}