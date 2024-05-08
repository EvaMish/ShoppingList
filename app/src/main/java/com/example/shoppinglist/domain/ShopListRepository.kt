package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addItem(shopItem: ShopItem)
    fun deleteItem(shopItem: ShopItem)
    fun editItem(shopItem: ShopItem)
    fun getItem(shopItemId:Int): ShopItem
    fun getShopList():LiveData<List<ShopItem>>
}