package com.example.miperfil.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.miperfil.data.model.VetService
import kotlinx.coroutines.flow.Flow

@Dao
interface VetServiceDao {
    @Query("SELECT * FROM vet_services ORDER BY price")
    fun getServicesFlow(): Flow<List<VetService>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServices(services: List<VetService>)

    @Query("SELECT COUNT(*) FROM vet_services")
    suspend fun getServiceCount(): Int
}







