package com.metamom.bbssample.sns


import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(private val oldList : List<SnsDto>,private val newList:List<SnsDto>): DiffUtil.Callback() {

    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}