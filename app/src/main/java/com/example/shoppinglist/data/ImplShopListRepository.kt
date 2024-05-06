package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ImplShopListRepository : ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0
    override fun addItem(shopItem: ShopItem) {
        if (shopItem.id==ShopItem.UNKNOWN_ID){
            shopItem.id= autoIncrementId++
        }
            shopList.add(shopItem)
    }
    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }
    override fun editItem(shopItem: ShopItem) {
        val oldElement = getItem(shopItem.id)
        shopList.remove(oldElement)
        addItem(shopItem)
    }
    override fun getItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("getItem returned null")
    }
    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}