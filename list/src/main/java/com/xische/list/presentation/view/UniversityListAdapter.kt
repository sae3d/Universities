package com.xische.list.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xische.common.presentation.uimodel.UniversityUiModel
import com.xische.list.databinding.ItemUniversityBinding


class UniversityListAdapter(val onUniversityClicked: (String) -> Unit) :
    ListAdapter<UniversityUiModel, UniversityListAdapter.UniversityViewHolder>(
        UniversityDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding =
            ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = getItem(position)
        holder.bind(university)
        holder.itemView.setOnClickListener {
            onUniversityClicked(university.name)
        }
    }

    inner class UniversityViewHolder(private val binding: ItemUniversityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(university: UniversityUiModel) {
            binding.apply {
                tvName.text = university.name
                tvState.text = university.state
            }
        }
    }
}

class UniversityDiffCallback : DiffUtil.ItemCallback<UniversityUiModel>() {
    override fun areItemsTheSame(oldItem: UniversityUiModel, newItem: UniversityUiModel): Boolean {
        return oldItem.name === newItem.name
    }

    override fun areContentsTheSame(
        oldItem: UniversityUiModel,
        newItem: UniversityUiModel
    ): Boolean {
        return oldItem == newItem
    }
}
