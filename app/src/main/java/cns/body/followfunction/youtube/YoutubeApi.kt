package bodygate.bcns.bodygation.youtube

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface YoutubeApi {
    @GET("search")
    fun searchVideo(@Query("part") part:String,
                    @Query("maxResults") maxResults:Int,
                    @Query("q") query:String,
                    @Query("regionCode") regionCode:String,
                    @Query("type") type:String,
                    @Query("order") order:String,
                    @Query("key") key:String,
                    @Query("videoDimension") videoDimension:String
    ):Call<YoutubeResponse>
    @GET("search")
    fun nextVideo(@Query("part") part:String,
                    @Query("maxResults") maxResults:Int,
                    @Query("q") query:String,
                    @Query("regionCode") regionCode:String,
                    @Query("type") type:String,
                    @Query("pageToken") pageToken:String,
                  @Query("order") order:String,
                    @Query("key") key:String,
                  @Query("videoDimension") videoDimension:String
    ):Call<YoutubeResponse>
    /*
        @POST("search")
        fun searchVideo(@Query("part") part:String,
                        @Query("maxResults") maxResults:Int,
                        @Query("q") query:String,
                        @Query("regionCode") regionCode:String,
                        @Query("type") type:String,
                        @Query("order") order:String,
                        @Query("key") key:String
        ):Call<YoutubeResponse>
        @POST("search")
        fun nextVideo(@Query("part") part:String,
                      @Query("maxResults") maxResults:Int,
                      @Query("q") query:String,
                      @Query("regionCode") regionCode:String,
                      @Query("type") type:String,
                      @Query("pageToken") pageToken:String,
                      @Query("order") order:String,
                      @Query("key") key:String
        ):Call<YoutubeResponse>
    */
    companion object Factory {
        val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        fun create(): YoutubeApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(YoutubeApi::class.java)
        }
    }
}