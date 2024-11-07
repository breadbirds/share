package com.ssafy.template.board.data.remote

import com.ssafy.template.board.base.ApplicationClass
import com.ssafy.template.board.data.model.search.SearchResult
import retrofit2.http.*

interface SearchRetrofitService {
    @GET("api/board/search")
    suspend fun searchBoard( @Query("keyword") keyword : String ): MutableList<SearchResult>


    /*******************/
    @GET("api/board")
    suspend fun selectAll(): MutableList<SearchResult>

    @GET("api/board/{no}")
    suspend fun selectBoard(@Path("no") no:String): SearchResult

    @DELETE("api/board/{no}")
    suspend fun deleteBoard(@Path("no") no:String): String

    @PUT("api/board/{no}")
    suspend fun updateBoard(@Path("no") no:String, @Body board: SearchResult): String

    @POST("api/board")
    suspend fun insertBoard(@Body board: SearchResult): String

}

object SearchApi {
    val searchRetrofitService : SearchRetrofitService by lazy {
        ApplicationClass.retrofit.create(SearchRetrofitService::class.java)
    }
}