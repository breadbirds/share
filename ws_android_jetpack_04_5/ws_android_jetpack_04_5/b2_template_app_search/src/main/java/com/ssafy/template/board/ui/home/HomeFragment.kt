package com.ssafy.template.board.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.databinding.FragmentHomeBinding
import com.ssafy.template.board.data.model.home.PostSignUpRequest

private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()

        binding.homeButtonTryGetJwt.setOnClickListener {
            showLoadingDialog()
            homeViewModel.getUserData()
        }

        binding.homeBtnTryPostHttpMethod.setOnClickListener {
            val userId = binding.homeEtId.text.toString()
            val password = binding.homeEtPw.text.toString()
            val postRequest = PostSignUpRequest(userId = userId, password = password)

            showLoadingDialog()
            homeViewModel.getSignUpUser(postRequest)
        }
    }

    private fun registerObserver(){
        homeViewModel.user.observe(viewLifecycleOwner){
            // 결과 size로 체크
            if(it.result.size != 0){
                binding.homeButtonTryGetJwt.text = it.message
                Log.d(TAG, it.toString())
                showCustomToast("${it.result}")
            }else{
                showCustomToast("오류...")
            }
            dismissLoadingDialog()
        }

        homeViewModel.signUpUser.observe(viewLifecycleOwner){
            // 결과 code 로 체크
            if(it.code != 0){
                binding.homeBtnTryPostHttpMethod.text = it.message
                Log.d(TAG, it.toString())
                showCustomToast("${it.result}")
            }else{
                showCustomToast("오류...")
            }
            dismissLoadingDialog()
        }

    }
}