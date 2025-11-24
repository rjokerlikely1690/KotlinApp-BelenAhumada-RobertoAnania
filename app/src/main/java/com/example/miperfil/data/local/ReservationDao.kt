package com.example.miperfil.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.miperfil.data.model.Reservation
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservations WHERE ownerEmail = :ownerEmail ORDER BY appointmentDate DESC")
    fun getReservationsForOwner(ownerEmail: String): Flow<List<Reservation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservation(reservation: Reservation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservations(reservations: List<Reservation>)
}


