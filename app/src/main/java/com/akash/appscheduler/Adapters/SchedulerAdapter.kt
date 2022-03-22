package com.akash.appscheduler.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.databinding.PackageInfoItemBinding


class SchedulerAdapter(val onScheduleClicked:()->Unit): RecyclerView.Adapter<SchedulerAdapter.SchedulerViewHolder>() {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PackageInfo>(){
        override fun areItemsTheSame(oldItem: PackageInfo, newItem: PackageInfo): Boolean {
            return oldItem.packageName == newItem.packageName
        }

        override fun areContentsTheSame(oldItem: PackageInfo, newItem: PackageInfo): Boolean {
            return oldItem == newItem;
        }

    }
    private val mDiffer: AsyncListDiffer<PackageInfo> = AsyncListDiffer<PackageInfo>(this, DIFF_CALLBACK)

    inner class SchedulerViewHolder(val binding:PackageInfoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        public fun bind(packageInfo: PackageInfo){
            binding.tvName.text = packageInfo.appName
            binding.tvVersion.text = "Version : " + packageInfo.versionName
            binding.tvVersionCode.text = "Version code : " + packageInfo.versionCode
            binding.roundedImageView.setImageDrawable(packageInfo.icon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PackageInfoItemBinding.inflate(inflater,parent,false)
        return SchedulerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchedulerViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val item = mDiffer.currentList[position]
            holder.bind(item)

            holder.binding.tvSchedule.setOnClickListener {
                onScheduleClicked()
            }
        }
    }



    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    fun submitList(list: List<PackageInfo>) {
        mDiffer.submitList(list)
    }
}