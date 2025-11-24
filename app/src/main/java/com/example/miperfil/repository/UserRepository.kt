package com.example.miperfil.repository

import com.example.miperfil.data.local.UserDao
import com.example.miperfil.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
    
    fun getUserByEmailFlow(email: String): Flow<User?> {
        return userDao.getUserByEmailFlow(email)
    }
    
    suspend fun saveUser(user: User) {
        userDao.insertUser(user)
    }
    
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}

