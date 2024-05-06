package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getItem(shopItemId:Int): ShopItem {
      return shopListRepository.getItem(shopItemId)
    }
}