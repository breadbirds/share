package com.ssafy.template.board.data.model.home

import com.google.gson.annotations.SerializedName

data class ResultUser(
    @SerializedName("userId") val userId: String,
    @SerializedName("name") val name: String
)
