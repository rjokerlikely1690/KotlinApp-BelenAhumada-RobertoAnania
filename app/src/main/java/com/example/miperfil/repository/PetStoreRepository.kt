package com.example.miperfil.repository

import com.example.miperfil.data.local.PetDao
import com.example.miperfil.data.local.ProductDao
import com.example.miperfil.data.local.ReservationDao
import com.example.miperfil.data.local.VetServiceDao
import com.example.miperfil.data.model.Pet
import com.example.miperfil.data.model.Product
import com.example.miperfil.data.model.Reservation
import com.example.miperfil.data.model.VetService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PetStoreRepository(
    private val petDao: PetDao,
    private val productDao: ProductDao,
    private val reservationDao: ReservationDao,
    private val vetServiceDao: VetServiceDao
) {

    fun getPets(ownerEmail: String): Flow<List<Pet>> = petDao.getPetsForOwner(ownerEmail)

    suspend fun addPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    fun getProducts(): Flow<List<Product>> = productDao.getProductsFlow()

    suspend fun addProduct(product: Product) {
        productDao.insertProduct(product)
    }

    fun getReservations(ownerEmail: String): Flow<List<Reservation>> =
        reservationDao.getReservationsForOwner(ownerEmail)

    suspend fun addReservation(reservation: Reservation) {
        reservationDao.insertReservation(reservation)
    }

    fun getServices(): Flow<List<VetService>> = vetServiceDao.getServicesFlow()

    suspend fun saveServices(services: List<VetService>) {
        vetServiceDao.insertServices(services)
    }

    suspend fun ensureSeedData(ownerEmail: String) {
        var ownerPets = petDao.getPetsForOwner(ownerEmail).first()
        if (ownerPets.isEmpty()) {
            ownerPets = listOf(
                Pet(
                    ownerEmail = ownerEmail,
                    name = "Luna",
                    breed = "Labrador",
                    age = 3,
                    weight = 24.5,
                    lastVisit = "12 Oct 2025",
                    photoUri = null
                ),
                Pet(
                    ownerEmail = ownerEmail,
                    name = "Rocky",
                    breed = "Beagle",
                    age = 5,
                    weight = 15.0,
                    lastVisit = "03 Sep 2025",
                    photoUri = null
                )
            )
            ownerPets.forEach { petDao.insertPet(it) }
        }

        if (productDao.getProductCount() == 0) {
            val sampleProducts = listOf(
                Product(
                    title = "Alimento Premium",
                    category = "Nutrición",
                    description = "Croquetas ricas en proteínas para perros activos.",
                    price = 34.990,
                    stock = 18,
                    featured = true
                ),
                Product(
                    title = "Collar GPS",
                    category = "Tecnología",
                    description = "Localiza a tu mascota en tiempo real desde la app.",
                    price = 54.990,
                    stock = 6,
                    featured = true
                ),
                Product(
                    title = "Shampoo Antialérgico",
                    category = "Higiene",
                    description = "Protege la piel sensible con avena natural.",
                    price = 12.990,
                    stock = 32
                )
            )
            productDao.insertProducts(sampleProducts)
        }

        if (vetServiceDao.getServiceCount() == 0) {
            val services = listOf(
                VetService(
                    title = "Consulta General",
                    description = "Evaluación completa + plan de vacunas.",
                    durationMinutes = 40,
                    price = 18.990
                ),
                VetService(
                    title = "Baño y Spa",
                    description = "Baño dermatológico, corte de uñas y masajes.",
                    durationMinutes = 60,
                    price = 22.500
                ),
                VetService(
                    title = "Urgencia 24/7",
                    description = "Atención prioritaria con traslado incluido.",
                    durationMinutes = 90,
                    price = 45.000
                )
            )
            vetServiceDao.insertServices(services)
        }

        if (reservationDao.getReservationsForOwner(ownerEmail).first().isEmpty()) {
            val firstPetName = ownerPets.firstOrNull()?.name ?: "Luna"
            val reservations = listOf(
                Reservation(
                    ownerEmail = ownerEmail,
                    petName = firstPetName,
                    serviceName = "Consulta General",
                    appointmentDate = System.currentTimeMillis() + 86_400_000,
                    status = "Confirmada"
                )
            )
            reservationDao.insertReservations(reservations)
        }
    }
}


