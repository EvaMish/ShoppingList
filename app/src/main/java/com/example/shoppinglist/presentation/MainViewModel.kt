package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ImplShopListRepository
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.usecase.GetShopListUseCase

class MainViewModel:ViewModel() {
    private val repositoryImpl=ImplShopListRepository///change

    val shopList= MutableLiveData<List<ShopItem>>()
    private val getShopListUseCase=GetShopListUseCase(repositoryImpl)

    fun getList(){
        val list =getShopListUseCase.getShopList()
        shopList.value=list
    }
}