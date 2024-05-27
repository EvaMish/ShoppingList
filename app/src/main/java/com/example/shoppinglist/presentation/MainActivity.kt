package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopList = findViewById(R.id.ll_shopList)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
            //Log.d("++++++++", it.toString())
        }
    }

    private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (item in list) {
            val layoutId = if (item.enabled) {
                R.layout.item_shop_enabled
            } else R.layout.item_shop_disabled

            val view = LayoutInflater.from(this).inflate(layoutId, llShopList, false)

            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount= view.findViewById<TextView>(R.id.tv_count)

            tvName.text=item.name
            tvCount.text=item.count.toString()

            view.setOnLongClickListener{
                viewModel.changeEnabledStateItem(item)
                true
            }
            llShopList.addView(view)

        }
    }
}

