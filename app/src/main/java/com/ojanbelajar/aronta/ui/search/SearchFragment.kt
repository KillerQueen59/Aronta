package com.ojanbelajar.aronta.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.databinding.FragmentHomeBinding
import com.ojanbelajar.aronta.databinding.FragmentSearchBinding
import com.ojanbelajar.aronta.ui.home.HomeViewModel
import com.ojanbelajar.aronta.ui.news.NewsAdapter
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.jetbrains.anko.support.v4.toast

@AndroidEntryPoint
class SearchFragment: Fragment() {

    lateinit var binding: FragmentSearchBinding
    lateinit var model: SearchViewModel
    lateinit var session: SessionManagement
    lateinit var adapter: SearchAdapter
    var isAny = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model =  ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        session = SessionManagement(requireContext())
        adapter = SearchAdapter(requireActivity().applicationContext)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRV()
        prepareSearch()
    }

    fun prepareSearch(){
        binding.searchBuruh.queryHint = "Find Your Worker"
        if (!isAny){
            blankRv()
        }
        binding.searchBuruh.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                if (p0.isNotEmpty()){
                    if (p0==""){

                        isAny = false
                        blankRv()
                    }else{
                        isAny = true
                        lifecycleScope.launch {
                            model.getWorkerSearch(p0,session.token).observe(viewLifecycleOwner,{ result ->
                                if (result != null){
                                    when(result){
                                        is Resource.Loading ->{
                                            binding.lnWait.visibility = View.GONE
                                            binding.lnProgressBar.visibility = View.VISIBLE
                                        }
                                        is Resource.Success -> {
                                            binding.lnWait.visibility = View.GONE
                                            binding.lnProgressBar.visibility = View.GONE
                                            binding.rvSearch.visibility = View.VISIBLE
                                            if (result.data!=null){
                                                adapter.setData(result.data)
                                            } else {
                                                binding.rvSearch.visibility = View.GONE
                                                binding.lnWait.visibility = View.VISIBLE
                                            }
                                        }
                                        is Resource.Error -> {
                                        }
                                    }
                                }
                            })
                        }
                    }
                } else{
                   isAny = false
                    blankRv()
                }
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return false
            }

        })
        binding.searchBuruh.setOnCloseListener(SearchView.OnCloseListener {
            isAny = false
            blankRv()
            true
        })
    }

    fun blankRv(){
        model.getWorker(session.token).observe(viewLifecycleOwner,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                        binding.lnWait.visibility = View.GONE
                        binding.lnProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.lnWait.visibility = View.GONE
                        binding.lnProgressBar.visibility = View.GONE
                        binding.rvSearch.visibility = View.VISIBLE
                        if (result.data!=null){
                            adapter.setData(result.data)
                        } else {
                            binding.rvSearch.visibility = View.GONE
                            binding.lnWait.visibility = View.VISIBLE
                        }
                    }
                    is Resource.Error -> {
                    }
                }
            }
        })
    }

    fun prepareRV(){
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        isAny = false

    }
}