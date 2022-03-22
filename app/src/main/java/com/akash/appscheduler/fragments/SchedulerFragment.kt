package com.akash.appscheduler.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.akash.appscheduler.Adapters.SchedulerAdapter
import com.akash.appscheduler.R
import com.akash.appscheduler.Viewmodels.MainViewModel
import com.akash.appscheduler.databinding.FragmentSchedulerBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SchedulerFragment : Fragment() {

    private lateinit var adapter: SchedulerAdapter
    val mainViewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentSchedulerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSchedulerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        mainViewModel.getPackageInfo().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        adapter = SchedulerAdapter()
        binding.packageHolderRv.layoutManager = layoutManager
        binding.packageHolderRv.adapter = adapter

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SchedulerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}