package com.example.miperfil.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ownerEmail: String,
    val name: String,
    val breed: String,
    val age: Int,
    val weight: Double,
    val lastVisit: String,
    val photoUri: String? = null
)







