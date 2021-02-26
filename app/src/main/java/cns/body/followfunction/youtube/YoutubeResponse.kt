package cns.body.followfunction.youtube

import com.google.gson.annotations.SerializedName



/**
 * Created by LeeBeomWoo on 2018-03-26.
 */
class YoutubeResponse {
    val kind: String? = null
    val etag: String? = null
    val nextPageToken: String? = null
    val pageInfo: PageInfo? = null
    val items: MutableList<Items> = ArrayList()
    fun addItems(item: Items){
        items.add(item)
    }
    class PageInfo {
        val totalResults: Int = 0
        val resultsPerPage: Int = 0
    }

    class Items {
        val kind: String? = null
        val etag: String? = null
        val snippet: Snippet? = null
        val id: Id? = null
        class Id{
            val kind: String? = null
            val videoId: String? = null
            val channelId: String? = null
            val playlistId: String? = null
        }

        class Snippet {
            val publishedAt: String? = null
            val channelId: String? = null
            val title: String? = null
            val description: String? = null
            val channelTitle: String? = null
            val categoryId: String? = null
            val liveBroadcastContent: String? = null
            val thumbnails: Thumbnails? = null
            val tags: List<String> = ArrayList()

            class Thumbnails {
                @SerializedName("high")
                val high: High? = null

                class High {
                    val url: String? = null
                    val width: Int = 0
                    val height: Int = 0
                }
            }
        }
    }


}