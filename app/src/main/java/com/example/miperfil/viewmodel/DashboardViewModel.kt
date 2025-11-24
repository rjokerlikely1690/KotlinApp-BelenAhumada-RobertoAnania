package com.example.miperfil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.miperfil.data.model.Pet
import com.example.miperfil.data.model.Product
import com.example.miperfil.data.model.Reservation
import com.example.miperfil.data.model.VetService
import com.example.miperfil.repository.PetStoreRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DashboardUiState(
    val ownerEmail: String? = null,
    val pets: List<Pet> = emptyList(),
    val products: List<Product> = emptyList(),
    val reservations: List<Reservation> = emptyList(),
    val services: List<VetService> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class DashboardViewModel(private val repository: PetStoreRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private var observeJob: Job? = null

    fun loadDashboard(ownerEmail: String) {
        if (_uiState.value.ownerEmail == ownerEmail && observeJob != null) return

        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, ownerEmail = ownerEmail, errorMessage = null) }

            try {
                repository.ensureSeedData(ownerEmail)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }

            combine(
                repository.getPets(ownerEmail),
                repository.getProducts(),
                repository.getReservations(ownerEmail),
                repository.getServices()
            ) { pets, products, reservations, services ->
                DashboardUiState(
                    ownerEmail = ownerEmail,
                    pets = pets,
                    products = products,
                    reservations = reservations,
                    services = services,
                    isLoading = false,
                    errorMessage = null
                )
            }.catch { throwable ->
                _uiState.update { it.copy(errorMessage = throwable.message, isLoading = false) }
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            repository.addPet(pet)
        }
    }

    fun addReservation(reservation: Reservation) {
        viewModelScope.launch {
            repository.addReservation(reservation)
        }
    }
}

class DashboardViewModelFactory(
    private val repository: PetStoreRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}







