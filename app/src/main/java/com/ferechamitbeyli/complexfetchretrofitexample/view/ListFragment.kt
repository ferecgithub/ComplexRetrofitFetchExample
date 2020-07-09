package com.ferechamitbeyli.complexfetchretrofitexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferechamitbeyli.complexfetchretrofitexample.R
import com.ferechamitbeyli.complexfetchretrofitexample.model.Data2
import com.ferechamitbeyli.complexfetchretrofitexample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val listAdapter = ListFragmentAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        list_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        swipeLayout.setOnRefreshListener {
            list_rv.visibility = View.GONE
            error_tv.visibility = View.GONE
            loading_pb.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            swipeLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dataList.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                list_rv.visibility = View.VISIBLE

                val arrayOfData2 = response.data1.children.map {
                   it.data2
                }.toList()
                listAdapter.updateList(arrayOfData2)

            }

        })

        viewModel.loadingError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                error_tv.visibility = if (it) View.VISIBLE else View.GONE
            }

        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loading_pb.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    error_tv.visibility = View.GONE
                    list_rv.visibility = View.GONE
                }

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        list_rv.adapter = null
    }

}