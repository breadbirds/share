package com.ssafy.template.board.ui.board

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.databinding.FragmentBoardBinding
import com.ssafy.template.board.databinding.FragmentBoardDetailBinding
import com.ssafy.template.board.ui.search.SearchViewModel


private const val TAG = "BoardDetailFragment_싸피"

class BoardDetailFragment : BaseFragment<FragmentBoardDetailBinding>(
    FragmentBoardDetailBinding::bind,
    R.layout.fragment_board_detail
) {

    private val searchViewModel: SearchViewModel by activityViewModels()
//    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = searchViewModel
//
//        binding.viewModel = searchViewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//        arguments?.let {
//            param1 = it.getInt("id")
//            Log.d(TAG, "onCreate: $param1")
//            searchViewModel.selectBoard(param1.toString())
//        }

//        searchViewModel.selectedBoard.observe(viewLifecycleOwner) { item ->
//            binding.tvNum.text = item.no.toString()
//            binding.etTitle.text = item.title
//            binding.etWriter.text = item.writer
//        }

        // 삭제 버튼
//        binding.btnDelete.setOnClickListener {
//
//        }
//
//        // 뒤로가기 버튼
//        binding.btnBack.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }

    }

}