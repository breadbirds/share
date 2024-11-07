package com.ssafy.template.board.data.model.search

data class SearchResult(
    val no: Int,
    var title: String,
    var content: String,
    var writer: String,
    val regtime: String
) {
    constructor() : this(0, "","","","")
}