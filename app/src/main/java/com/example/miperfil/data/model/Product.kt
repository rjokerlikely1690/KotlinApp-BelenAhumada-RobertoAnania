package com.example.miperfil.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val category: String,
    val description: String,
    val price: Double,
    val stock: Int,
    val imageUrl: String? = null,
    val featured: Boolean = false
)







