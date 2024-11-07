package com.ssafy.template.board.ui.board

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.databinding.FragmentBoardBinding
import com.ssafy.template.board.databinding.ListItemDatabindingBinding
import com.ssafy.template.board.ui.search.SearchFragmentWithListAdapterAndDataBinding.MyListAdapter
import com.ssafy.template.board.ui.search.SearchViewModel


private const val TAG = "BoardFragment_싸피"

// BoardFragment.kt
class BoardFragment :
    BaseFragment<FragmentBoardBinding>(FragmentBoardBinding::bind, R.layout.fragment_board) {

    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // MyListAdapter 초기화 시 클릭 리스너 설정
        val adapter = MyListAdapter { itemId ->
            // 클릭된 아이템의 ID를 BoardDetailFragment로 전달하면서 전환

            searchViewModel.selectBoard(itemId.toString())
//            val bundle = Bundle().apply {
//                Log.d(TAG, "onViewCreated: $itemId")
//                putInt("id", itemId)
//            }
//            val fragment = BoardDetailFragment().apply {
//                arguments = bundle
//            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, BoardDetailFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        searchViewModel.selectAllBoard()

        // RecyclerView와 Adapter 세팅
        binding.boardFragRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        // FAB 버튼 클릭 시 BoardAddFragment로 이동
        binding.fabBtnBoard.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, BoardAddFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    // MyListAdapter 정의
    class MyListAdapter(private val onClickItem: (Int) -> Unit) :
        ListAdapter<SearchResult, MyListAdapter.CustomViewHolder>(SearchResultComparator) {

        companion object SearchResultComparator : DiffUtil.ItemCallback<SearchResult>() {
            override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                return oldItem.no == newItem.no
            }

            override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                return oldItem == newItem
            }
        }

        inner class CustomViewHolder(
            private val binding: ListItemDatabindingBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data: SearchResult) {
                binding.apply {
                    searchResult = data
                }

                // 클릭 리스너 설정
                binding.root.setOnClickListener {
                    Log.d(TAG, "bindInfo: ${data.no}")
                    onClickItem(data.no) // 클릭된 항목의 ID 전달
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val binding = ListItemDatabindingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CustomViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.bindInfo(getItem(position))
        }
    }
}
