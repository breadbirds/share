package com.ssafy.template.board.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.data.remote.SearchApi
import kotlinx.coroutines.launch

private const val TAG = "SearchViewModels_싸피"

class SearchViewModel : ViewModel() {
    private val _searchResult = MutableLiveData<MutableList<SearchResult>>()
    val searchResult: LiveData<MutableList<SearchResult>>
        get() = _searchResult

    fun searchBoard(id: String) {
        viewModelScope.launch {
            try {
                _searchResult.value = SearchApi.searchRetrofitService.searchBoard(id)
            } catch (e: Exception) {
                _searchResult.value = ArrayList()
            }
        }
    }


    private val _selectedBoard = MutableLiveData<SearchResult>()
    val selectedBoard: LiveData<SearchResult> get() = _selectedBoard

    fun selectBoard(id: String) {
        viewModelScope.launch {
            try {
                val board = SearchApi.searchRetrofitService.selectBoard(id)
                _selectedBoard.value = board

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun selectAllBoard() {
        viewModelScope.launch {
            try {
                _searchResult.value = SearchApi.searchRetrofitService.selectAll()
                Log.d(TAG, "searchBoard: ${_searchResult.value}")
            } catch (e: Exception) {
                _searchResult.value = ArrayList()
            }
        }
    }
}
