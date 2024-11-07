package com.ssafy.template.board.ui.board

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.databinding.FragmentBoardAddBinding
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date

private const val TAG = "BoardAddFragment_싸피"
class BoardAddFragment : BaseFragment<FragmentBoardAddBinding>(
    FragmentBoardAddBinding::bind,
    R.layout.fragment_board_add
) {

    private val boardViewModel: BoardViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSave.setOnClickListener {
            val num = 0 // 제대로된 숫자 변환
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            val writer = binding.etWriter.text

            val boardData = SearchResult(num, title, content, "ssafy", getCurrentTimeInISO())
            Log.d(TAG, "onViewCreated: $boardData")
            boardViewModel.insertBoard(boardData)
            parentFragmentManager.popBackStack()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTimeInISO(): String {
        val currentTime = Instant.now() // 현재 UTC 시간
        val formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneOffset.UTC) // UTC 타임존 설정
        return formatter.format(currentTime)
    }

}