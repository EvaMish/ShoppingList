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
    private val repository = ImplShopListRepository
    val shopListLiveData = MutableLiveData<List<ShopItem>>()

    val getShopListUseCase = GetShopListUseCase(repository)
    val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    val editShopItemUseCase = EditShopItemUseCase(repository)

    fun deleteItemShopList(shopItem:ShopItem){
        deleteShopItemUseCase.deleteItem(shopItem)
    }
    fun changeEnabledStateItem(shopItem:ShopItem){
        val newItem=shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editItem(newItem)
    }
}