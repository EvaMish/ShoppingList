package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        //вызовет пару раз(то, что видит пользователь)
        // +пару штук снизу и сверху(для скрола)
        //не вызовет для каждого элемента, то есть допустим 10 000 раз, как это было бы в случае с
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disabled,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {//count items collection
        return shopList.size
    }

    override fun onBindViewHolder(
        holder: ShopItemViewHolder,
        position: Int
    ) {//будет вызван для каждого элемента в списке, поэтому поиск вьюэлемента вынесено в другую функцию
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) {
            "active"
        } else {
            "no active"
        }
        if (shopItem.enabled) {
            holder.tvName.text = "${shopItem.name} $status"
            holder.tvCount.text = shopItem.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, R.color.black))
        }
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.tvName.text = ""
        holder.tvCount.text = ""
        holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, R.color.white))

    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }

}

