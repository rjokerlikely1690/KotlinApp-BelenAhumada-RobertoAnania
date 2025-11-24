package com.example.miperfil.ui.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import com.example.miperfil.R
import com.example.miperfil.data.model.Product
import com.google.android.material.chip.Chip

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvProductDescription)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val chipCategory: Chip = itemView.findViewById(R.id.chipCategory)
        private val chipStock: Chip = itemView.findViewById(R.id.chipStock)

        fun bind(product: Product) {
            tvTitle.text = product.title
            tvDescription.text = product.description
            tvPrice.text = "$${String.format("%,.0f", product.price)}"
            chipCategory.text = product.category
            chipStock.text = "Stock ${product.stock}"
            val colorRes = if (product.stock > 10) {
                R.color.brand_surface_variant
            } else {
                R.color.brand_warning
            }
            chipStock.chipBackgroundColor = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, colorRes)
            )
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
        }
    }
}


