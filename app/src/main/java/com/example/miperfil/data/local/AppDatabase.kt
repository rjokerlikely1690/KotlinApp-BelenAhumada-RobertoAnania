package com.example.miperfil.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.miperfil.data.model.Pet
import com.example.miperfil.data.model.Product
import com.example.miperfil.data.model.Reservation
import com.example.miperfil.data.model.User
import com.example.miperfil.data.model.VetService

@Database(
    entities = [User::class, Pet::class, Product::class, VetService::class, Reservation::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun petDao(): PetDao
    abstract fun productDao(): ProductDao
    abstract fun vetServiceDao(): VetServiceDao
    abstract fun reservationDao(): ReservationDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "miperfil_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

