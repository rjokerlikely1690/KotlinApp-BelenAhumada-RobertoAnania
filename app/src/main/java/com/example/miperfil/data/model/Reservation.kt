package com.example.miperfil.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ownerEmail: String,
    val petName: String,
    val serviceName: String,
    val appointmentDate: Long,
    val status: String
)







