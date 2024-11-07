package com.ssafy.template.board.data.model.home

import com.google.gson.annotations.SerializedName
import com.ssafy.template.board.base.BaseResponse

//BaseResponse 상속
data class UserResponse(
    @SerializedName("result") val result: ArrayList<ResultUser>
) : BaseResponse()
