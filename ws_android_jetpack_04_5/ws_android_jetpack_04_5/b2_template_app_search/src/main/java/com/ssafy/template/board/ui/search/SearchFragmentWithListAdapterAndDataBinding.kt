package com.ssafy.template.board.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.databinding.FragmentSearchDatabindingBinding
import com.ssafy.template.board.databinding.ListItemDatabindingBinding
import com.ssafy.template.board.data.model.search.SearchResult

// Databinding, BindingAdapter를 이용한 구현.
private const val TAG = "SearchFragment_싸피"
class SearchFragmentWithListAdapterAndDataBinding : BaseFragment<FragmentSearchDatabindingBinding>(FragmentSearchDatabindingBinding::bind, R.layout.fragment_search_databinding) {

    private val searchViewModel: SearchViewModel by activityViewModels()

    //adapter구성
    var mAdapter = MyListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        registerObserver() // databinding이므로 observe 필요없음.

        // !! 필수 !!
        // databinding의 lifycycle을 현재 fragment의 viewModel과 lifecyle을 맞춤
        binding.lifecycleOwner = viewLifecycleOwner
        // databinding객체는 기본적으로 lifecycleOwner가 없다(null). 여기서는
        // viewModel이 살아 있는 동안에 결과를 받아야 하므로, lifecycleOwner를 view로 맞춰준다.

        //binding에 viewModel assign!!
        binding.viewModel = searchViewModel

        //adapter 세팅
        binding.searchFragRecyclerView.apply{
            this.adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        // IME 키패드에서 검색 버튼 클릭 이벤트
        binding.searchFragEt.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> { // 검색버튼 클릭
                    val keyword = binding.searchFragEt.text.toString()
//                    showLoadingDialog(requireContext())
                    searchViewModel.searchBoard(keyword)

                    //keyboard  내리기
                    val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm?.hideSoftInputFromWindow(binding.searchFragEt.getWindowToken(), 0)

                    true
                }

                else -> false //기타키 동작 없음.
            }
        }
    }
    //databinding되어 있으므로.. 필요없어짐.
//    private fun registerObserver(){
//        searchViewModel.searchResult.observe(viewLifecycleOwner){
//            if(it.size > 0){
//                Log.d(TAG, "registerObserver: $it")
//                mAdapter.submitList(it)
////                mAdapter.list = it
////                mAdapter.notifyDataSetChanged()
//            }
//            dismissLoadingDialog()
//        }
//    }

    class MyListAdapter: ListAdapter<SearchResult, MyListAdapter.CustomViewHolder>(
        SearchResultComparator
    ){
//        var list = mutableListOf<SearchResult>()
        companion object SearchResultComparator: DiffUtil.ItemCallback<SearchResult>() {
            // 이전 목록과 새 목록 두 객체가 동일한 항목을 나타내는지 여부를 확인
            override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                return oldItem.no == newItem.no
            }
            // 두 객체가 동일한 데이터를 가지고 있는지 확인
            override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                return oldItem == newItem
            }
        }

        class CustomViewHolder (val binding: ListItemDatabindingBinding): RecyclerView.ViewHolder(binding.root) {

            fun bindInfo(data: SearchResult){
                binding.apply{
                    searchResult = data
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val binding:ListItemDatabindingBinding = ListItemDatabindingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            return CustomViewHolder(binding).apply{
                binding.root.setOnClickListener{
                    Toast.makeText(parent.context, "onCreateViewHolder: adapterPosition:${adapterPosition}, layoutPosition: ${layoutPosition}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            Log.d(TAG, "onBindViewHolder: ${getItem(position)}")
            holder.bindInfo(getItem(position))
        }
    }
}

