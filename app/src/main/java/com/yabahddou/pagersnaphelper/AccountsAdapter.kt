package com.yabahddou.pagersnaphelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

/**
 ***************************************
 * Created by Abdelhadi on 2019-06-01.
 ***************************************
 */
class AccountsAdapter(itemsCount: Int = 3) : RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    private val productStore = listOf(
        Product("123456781234567812345678", "Product 1"),
        Product("123456781234567812345677", "Product 2"),
        Product("123456781234567812345676", "Product 3"),
        Product("123456781234567812345675", "Product 4"),
        Product("123456781234567812345674", "Product 5"),
        Product("123456781234567812345673", "Product 6"),
        Product("123456781234567812345673", "Product 7"),
        Product("123456781234567812345673", "Product 8")
    )

    var currentSelectedItem = -1

    var products = productStore.subList(0, min(productStore.size, itemsCount))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtHolderName = view.findViewById<TextView>(R.id.txtHolderName)
        val txtIban = view.findViewById<TextView>(R.id.txtIban)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)

        init {
            checkBox.setOnClickListener {
                val previousSelectedPos = currentSelectedItem
                currentSelectedItem = if (checkBox.isChecked) adapterPosition else -1
                notifyItemChanged(previousSelectedPos)
                notifyItemChanged(currentSelectedItem)
            }
        }

        fun bind(account: Product) {
            txtHolderName.text = account.productName
            txtIban.text = account.productId
            checkBox.isChecked = adapterPosition == currentSelectedItem
        }

    }
}