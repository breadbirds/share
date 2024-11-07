package com.ssafy.template.board.data.model.home

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("pwd") val password: String
)