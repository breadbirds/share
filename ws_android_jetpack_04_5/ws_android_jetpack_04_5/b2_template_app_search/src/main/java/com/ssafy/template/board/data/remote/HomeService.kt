package com.ssafy.template.board.data.remote

import com.ssafy.template.board.base.ApplicationClass
import com.ssafy.template.board.data.model.home.PostSignUpRequest
import com.ssafy.template.board.data.model.home.SignUpResponse
import com.ssafy.template.board.data.model.home.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val TAG = "HomeService_싸피"
interface HomeService {
    @GET("api/user/users")
    suspend fun getUsers() : UserResponse

    @POST("api/user/login")
    suspend fun postSignUp(@Body params: PostSignUpRequest): SignUpResponse
}



object HomeApi {
    val homeRetrofitService : HomeService by lazy {
        ApplicationClass.retrofit.create(HomeService::class.java)
    }
}