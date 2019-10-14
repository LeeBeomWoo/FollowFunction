package bodygate.bcns.bodygation.dummy

import android.annotation.SuppressLint
import com.google.android.youtube.player.YouTubePlayer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

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
        ITEM_MAP[item.date] = item
    }

    @SuppressLint("SimpleDateFormat")
    fun createDummyItem(position: Int): DummyItem {
        val label = SimpleDateFormat("MM/dd")
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_WEEK, position)
        val dayNum = cal.get(Calendar.DAY_OF_WEEK)
        var day = ""
        when(dayNum){
            1-> {
                day = "일"}
            2-> {
                day = "월"}
            3  -> {
                day = "화"}
            4 -> {
                day = "수"}
            5 -> {
                day = "목"}
            6 -> {
                day = "금"}
            7 -> {
                day = "토"}

        }
        return DummyItem(label.format(cal.timeInMillis), day + "요일")
    }

    /**
     * A dummy item representing a piece of content.
     */
    class DummyItem(val date: String, val dow: String) {
        override fun toString(): String {
            return date + "\n" + dow
        }
        fun todate():String{
            return date
        }
    }
}
