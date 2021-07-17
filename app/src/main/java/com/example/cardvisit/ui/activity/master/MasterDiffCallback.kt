package com.example.cardvisit.ui.activity.master

import androidx.recyclerview.widget.DiffUtil
import com.example.cardvisit.data.entity.Card

class MasterDiffCallback : DiffUtil.ItemCallback<Card>()  {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.localId == newItem.localId &&
                oldItem.remoteId == newItem.remoteId
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}