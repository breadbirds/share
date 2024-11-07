package com.ssafy.template.board.ui.myPage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.databinding.FragmentMyPageBinding

private const val TAG = "MyPageFragment"
class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()

        binding.buttonChangeCounterText.setOnClickListener {
            myPageViewModel.increaseCount()
        }
    }

    private fun registerObserver() {
        myPageViewModel.count.observe(viewLifecycleOwner) {
            Log.d(TAG, "registerObserver: $it")
            binding.textViewCounter.text = resources.getString(R.string.my_page_tv_counter, it)
        }
    }
}