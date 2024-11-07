package com.ssafy.template.board.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.template.board.R
import com.ssafy.template.board.base.BaseFragment
import com.ssafy.template.board.databinding.FragmentSearchBinding
import com.ssafy.template.board.data.model.search.SearchResult
import com.ssafy.template.board.databinding.ListItemDatabindingBinding


private const val TAG = "SearchFragment_싸피"
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by activityViewModels()

    //adapter구성
    var mAdapter = MyAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
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
                    showLoadingDialog()
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

    private fun registerObserver(){
        searchViewModel.searchResult.observe(viewLifecycleOwner){
            if(it.size > 0){
                Log.d(TAG, "registerObserver: $it")
                mAdapter.list = it
                mAdapter.notifyDataSetChanged()
            }
            dismissLoadingDialog()
        }
    }


    class MyAdapter: RecyclerView.Adapter<MyAdapter.CustomViewHolder>(){
        var list = mutableListOf<SearchResult>()

        //****** no databinding
//        class CustomViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
//            fun bindInfo(data: SearchResult) {
//                itemView.findViewById<TextView>(R.id.no).text = data.no.toString()
//                itemView.findViewById<TextView>(R.id.title).text = data.title
//                itemView.findViewById<TextView>(R.id.regDate).text = data.regtime
//            }
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//            return CustomViewHolder(view)
//        }
        //****** no databinding

        //****** databinding
        class CustomViewHolder (private val binding: ListItemDatabindingBinding): RecyclerView.ViewHolder(binding.root) {
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
        //****** databinding

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            Log.d(TAG, "onBindViewHolder: ${list.get(position)}")
            holder.bindInfo(list.get(position))
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}


