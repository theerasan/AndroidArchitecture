package news.ta.com.news.slice

import android.annotation.TargetApi
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.graphics.drawable.IconCompat

import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import news.ta.com.news.R
import news.ta.com.news.feature.NewsApplication
import news.ta.com.news.feature.main.MainActivity

class NewsSlice : SliceProvider() {

    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    /**
     * Converts URL to content URI (i.e. content://news.ta.com.news...)
     */
    override fun onMapIntentToUri(intent: Intent?): Uri {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
        if (intent == null) return uriBuilder.build()
        val data = intent.data
        if (data != null && data.path != null) {
            val path = data.path.replace("/newsslice", "")
            uriBuilder = uriBuilder.path(path)
        }
        val context = context
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.packageName)
        }
        return uriBuilder.build()
    }

    /**
     * Construct the Slice and bind data if available.
     */
    @TargetApi(Build.VERSION_CODES.M)
    override fun onBindSlice(sliceUri: Uri): Slice? {
        val context = context ?: return null

        val intent = Intent(getContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(getContext(), sliceUri.hashCode(),
                intent, 0)
        val openTempActivity = SliceAction(pendingIntent,
                IconCompat.createWithResource(context, R.drawable.ic_home).toIcon(),
                "Temperature controls")

        return if (sliceUri.path == "/newsslice" && NewsApplication.news != null) {
            // Path recognized. Customize the Slice using the androidx.slice.builders API.
            // Note: ANR and StrictMode are enforced here so don't do any heavy operations. 
            // Only bind data that is currently available in memory.

            ListBuilder(context, sliceUri)
                    .addRow {
                        it.setTitle(NewsApplication.news!!.headline)
                        it.setSubtitle(NewsApplication.news!!.description)
                        it.setPrimaryAction(openTempActivity)
                    }
                    .build()
        } else {
            // Error: Path not found.
            ListBuilder(context, sliceUri)
                    .addRow {
                        it.setTitle("No recently views")
                        it.setPrimaryAction(openTempActivity)
                    }
                    .build()
        }
    }

    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    override fun onSlicePinned(sliceUri: Uri?) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger NewsSlice#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    override fun onSliceUnpinned(sliceUri: Uri?) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}
