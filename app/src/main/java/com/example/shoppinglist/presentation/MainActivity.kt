package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.shopList = it
            //Log.d("++++++++", it.toString())
        }
    }

    private fun setUpRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ENABLED_VIEW_TYPE,
                ShopListAdapter.VIEW_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.DISABLED_VIEW_TYPE,
                ShopListAdapter.VIEW_POOL_SIZE
            )
            setUpClickListener()
            swipeAndDeleteCard(rvShopList)
        }
    }

    private fun setUpClickListener(){
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnabledStateItem(it)
        }
        shopListAdapter.onClickShopItem = {
            Log.d("6666", "$it")
        }

    }

    private fun swipeAndDeleteCard(rvShopList:RecyclerView){
        val callBack = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.shopList[viewHolder.adapterPosition]
                viewModel.deleteShopItemUseCase.deleteItem(item)
            }

        }

        val itemTouch=ItemTouchHelper(callBack)
        itemTouch.attachToRecyclerView(rvShopList)
    }

}
