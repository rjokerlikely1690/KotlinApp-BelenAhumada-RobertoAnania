package com.example.miperfil.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vet_services")
data class VetService(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val price: Double
)







