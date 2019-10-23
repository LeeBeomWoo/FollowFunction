package bodygate.bcns.bodygation.dummy

import com.google.android.youtube.player.YouTubePlayer
import com.google.api.services.youtube.model.SearchResult
import com.google.api.services.youtube.model.Thumbnail
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object listContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }
    /**
     * A dummy item representing a piece of content.
     */
    class DummyItem(val id: String, val title: String, val details: String) {
    }
}
