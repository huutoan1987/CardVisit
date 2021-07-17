package com.example.cardvisit.ui.activity.master

import androidx.recyclerview.widget.RecyclerView
import com.example.cardvisit.data.entity.Card
import com.example.cardvisit.databinding.LayoutMasterListRowBinding

class MasterVH(private val _binding: LayoutMasterListRowBinding,
               private val _vm: MasterVM, private val _isTablet: Boolean) :
    RecyclerView.ViewHolder(_binding.root) {

    fun bind(item: Card) {
        _binding.item = item
        _binding.vm = _vm
        _binding.isTablet = _isTablet
        _binding.executePendingBindings()
    }
}