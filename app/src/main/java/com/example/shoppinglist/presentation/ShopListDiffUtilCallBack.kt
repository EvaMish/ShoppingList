package com.example.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.domain.ShopItem

class ShopListDiffUtilCallBack(
    private val oldList:List<ShopItem>,
    private val newList:List<ShopItem>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if(oldItemPosition==newItemPosition){
            true
        }else false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }
}