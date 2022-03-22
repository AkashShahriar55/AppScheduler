package com.akash.appscheduler.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akash.appscheduler.Adapters.SchedulerAdapter
import com.akash.appscheduler.R
import com.akash.appscheduler.Viewmodels.MainViewModel
import com.akash.appscheduler.databinding.FragmentSchedulerBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


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
        adapter = SchedulerAdapter({
            val dialogView = View.inflate(activity, R.layout.date_time_picker, null)
            val alertDialog: AlertDialog = AlertDialog.Builder(activity).create()

            dialogView.findViewById<View>(R.id.date_time_set).setOnClickListener {
                val datePicker = dialogView.findViewById<View>(R.id.date_picker) as DatePicker
                val timePicker = dialogView.findViewById<View>(R.id.time_picker) as TimePicker
                val calendar: Calendar = GregorianCalendar(
                    datePicker.year,
                    datePicker.month,
                    datePicker.dayOfMonth,
                    timePicker.currentHour,
                    timePicker.currentMinute
                )
                alertDialog.dismiss()
            }
            alertDialog.setView(dialogView)
            alertDialog.show()
        })
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