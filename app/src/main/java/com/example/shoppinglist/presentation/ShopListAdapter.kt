package com.example.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    companion object {
        const val ENABLED_VIEW_TYPE = 0
        const val DISABLED_VIEW_TYPE = 1
    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var count = 0
    var status = "active"

    override fun onCreateViewHolder(
        //вызовет пару раз(то, что видит пользователь)
        // +пару штук снизу и сверху(для скрола)
        //не вызовет для каждого элемента, то есть допустим 10 000 раз, как это было бы в случае с
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder {
        Log.d("++++++++++++", "onCreateViewHolder:${count++} ")
        // для наглядности, что create вызовется не для каждого элемента
        val layout = when (viewType) {
            ENABLED_VIEW_TYPE -> R.layout.item_shop_enabled
            DISABLED_VIEW_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type:$viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: ShopItemViewHolder,
        position: Int
    ) {//будет вызван для каждого элемента в списке, поэтому поиск вьюэлемента вынесено в другую функцию
        val shopItem = shopList[position]
        status = if (shopItem.enabled) {
            "active"
        } else {
            "no active"
        }
        holder.view.setOnLongClickListener {
            true
        }
        holder.tvName.text = "${shopItem.name} $status"
        holder.tvCount.text = shopItem.count.toString()
    }

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.tvName.text = ""
        holder.tvCount.text = ""
        holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, R.color.white))

    }

    override fun getItemCount(): Int {//count items collection
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled)
            ENABLED_VIEW_TYPE
        else DISABLED_VIEW_TYPE
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }

}

