package com.ssafy.template.board.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.template.board.ui.search.SearchFragmentWithListAdapterAndDataBinding
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.ui.board.BoardFragment


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SearchResult>?) {
    val adapter = recyclerView.adapter as SearchFragmentWithListAdapterAndDataBinding.MyListAdapter
    adapter.submitList(data)
}

@BindingAdapter("boardData")
fun bindBoardRecyclerView(recyclerView: RecyclerView, data: List<SearchResult>?) {
    val adapter = recyclerView.adapter as BoardFragment.MyListAdapter
    adapter.submitList(data)
}