package com.ssafy.template.board.ui.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    fun increaseCount(){
        _count.value = (count.value ?: 0) + 1
    }

    fun decreaseCount(){
        _count.value = (count.value ?:0 ) -1
    }
}