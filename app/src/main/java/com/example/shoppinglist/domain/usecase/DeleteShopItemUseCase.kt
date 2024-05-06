package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteItem(shopItem: ShopItem){
        shopListRepository.deleteItem(shopItem)
    }
}