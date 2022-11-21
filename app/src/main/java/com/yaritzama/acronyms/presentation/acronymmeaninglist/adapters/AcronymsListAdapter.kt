package com.yaritzama.acronyms.presentation.acronymmeaninglist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yaritzama.acronyms.databinding.ItemMeaningBinding
import com.yaritzama.acronyms.domain.models.AcronymMeaningModel

class AcronymsListAdapter(): ListAdapter<AcronymMeaningModel,
        AcronymsListAdapter.AcronymMeaningViewHolder>(AcronymMeaningDiffUtil()){

    inner class AcronymMeaningViewHolder(
        private val binding: ItemMeaningBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(meaning: AcronymMeaningModel){
           with(binding){
               txtMeaning.text = meaning.sf
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymMeaningViewHolder {
        return AcronymMeaningViewHolder(ItemMeaningBinding.inflate(LayoutInflater.from(parent.context)
        ,parent, false))
    }

    override fun onBindViewHolder(holder: AcronymMeaningViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class AcronymMeaningDiffUtil: DiffUtil.ItemCallback<AcronymMeaningModel>() {
        override fun areItemsTheSame(
            oldItem: AcronymMeaningModel,
            newItem: AcronymMeaningModel
        ): Boolean {
            return oldItem.sf == newItem.sf
        }

        override fun areContentsTheSame(
            oldItem: AcronymMeaningModel,
            newItem: AcronymMeaningModel
        ): Boolean {
            return oldItem == newItem
        }

    }
}