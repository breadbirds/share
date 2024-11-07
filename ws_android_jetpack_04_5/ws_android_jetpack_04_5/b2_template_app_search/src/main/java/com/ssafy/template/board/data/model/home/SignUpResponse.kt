package com.ssafy.template.board.data.model.home

import com.google.gson.annotations.SerializedName
import com.ssafy.template.board.base.BaseResponse

data class SignUpResponse(
    @SerializedName("result") val result: ResultUser
) : BaseResponse()