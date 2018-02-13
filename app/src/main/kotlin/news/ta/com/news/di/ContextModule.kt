package news.ta.com.news.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var context: Context) {

    @Provides
    fun provideContext(): Context = context
}