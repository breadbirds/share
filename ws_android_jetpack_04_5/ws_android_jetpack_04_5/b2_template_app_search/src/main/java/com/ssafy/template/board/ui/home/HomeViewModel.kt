package com.ssafy.template.board.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.template.board.data.model.home.PostSignUpRequest
import com.ssafy.template.board.data.model.home.ResultUser
import com.ssafy.template.board.data.model.home.SignUpResponse
import com.ssafy.template.board.data.model.home.UserResponse
import com.ssafy.template.board.data.remote.HomeApi
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _user = MutableLiveData<UserResponse>()
    val user:LiveData<UserResponse>
        get() = _user

    private val _signUpUser = MutableLiveData<SignUpResponse>()
    val signUpUser:LiveData<SignUpResponse>
        get() = _signUpUser

    fun getUserData(){
        viewModelScope.launch {
            try {
                _user.value = HomeApi.homeRetrofitService.getUsers()
            } catch (e: Exception) {
                _user.value = UserResponse(ArrayList())
            }
        }
    }

    fun getSignUpUser(postSignUpRequest: PostSignUpRequest){
        viewModelScope.launch {
            try {
                _signUpUser.value = HomeApi.homeRetrofitService.postSignUp(postSignUpRequest)
            } catch (e: Exception) {
                _signUpUser.value = SignUpResponse(ResultUser("",""))
            }
        }
    }
}
