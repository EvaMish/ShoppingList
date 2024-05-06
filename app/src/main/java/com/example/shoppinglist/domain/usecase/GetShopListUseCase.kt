package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()

    }
}