package com.example.shoppinglist.domain

data class ShopItem(
    val name: String,
    val enabled: Boolean,
    val count: Int,
    var id: Int=UNKNOWN_ID,
){
    companion object{
        const val UNKNOWN_ID=-1
    }
}