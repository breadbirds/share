package com.ssafy.template.board.ui.board

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.data.remote.HomeApi
import com.ssafy.template.board.data.remote.SearchApi
import kotlinx.coroutines.launch

private const val TAG = "BoardViewModel_싸피"
class BoardViewModel : ViewModel() {

    private val _board = MutableLiveData<SearchResult>()
    val board: LiveData<SearchResult>
        get() = _board


    fun insertBoard(board: SearchResult) {
        viewModelScope.launch {
            try {
                val res = SearchApi.searchRetrofitService.insertBoard(board)
                Log.d(TAG, "insertBoard: $res")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(TAG, "insertBoard: ${e.printStackTrace()}")

            }
        }
    }

}