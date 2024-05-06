package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editItem(shopItem: ShopItem) {
        shopListRepository.editItem(shopItem)
    }
}