package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ImplShopListRepository
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.usecase.DeleteShopItemUseCase
import com.example.shoppinglist.domain.usecase.EditShopItemUseCase
import com.example.shoppinglist.domain.usecase.GetShopListUseCase

class MainViewModel : ViewModel() {
    private val repositoryImpl = ImplShopListRepository///change
    private val getShopListUseCase = GetShopListUseCase(repositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(repositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repositoryImpl)
    val shopList = getShopListUseCase.getShopList()

    fun deleteItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editItem(newItem)
    }
}