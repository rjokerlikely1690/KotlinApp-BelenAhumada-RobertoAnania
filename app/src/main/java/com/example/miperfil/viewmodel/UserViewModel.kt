package com.example.miperfil.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miperfil.data.model.User
import com.example.miperfil.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            try {
                val user = repository.getUserByEmail(email)
                if (user != null && user.password == password) {
                    _currentUser.value = user
                    _saveSuccess.value = true
                } else {
                    _errorMessage.value = "Credenciales incorrectas"
                    _saveSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al iniciar sesión: ${e.message}"
                _saveSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun registerUser(user: User) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            try {
                repository.saveUser(user)
                _currentUser.value = user
                _saveSuccess.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Error al registrar usuario: ${e.message}"
                _saveSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun updateUserProfile(user: User) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            try {
                repository.updateUser(user)
                _currentUser.value = user
                _saveSuccess.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar perfil: ${e.message}"
                _saveSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadUser(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = repository.getUserByEmail(email)
                _currentUser.value = user
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar usuario: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun clearState() {
        _currentUser.value = null
        _errorMessage.value = null
        _saveSuccess.value = false
    }
}

