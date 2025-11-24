package com.example.miperfil.ui

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miperfil.R
import com.example.miperfil.data.local.AppDatabase
import com.example.miperfil.data.model.Pet
import com.example.miperfil.data.model.Reservation
import com.example.miperfil.data.model.Product
import com.example.miperfil.repository.PetStoreRepository
import com.example.miperfil.ui.adapters.PetAdapter
import com.example.miperfil.ui.adapters.ProductAdapter
import com.example.miperfil.ui.adapters.ReservationAdapter
import com.example.miperfil.viewmodel.DashboardViewModel
import com.example.miperfil.viewmodel.DashboardViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels {
        val database = AppDatabase.getDatabase(this)
        DashboardViewModelFactory(
            PetStoreRepository(
                database.petDao(),
                database.productDao(),
                database.reservationDao(),
                database.vetServiceDao()
            )
        )
    }

    private lateinit var toolbar: MaterialToolbar
    private lateinit var petsAdapter: PetAdapter
    private lateinit var productsAdapter: ProductAdapter
    private lateinit var reservationsAdapter: ReservationAdapter
    private lateinit var petsRecyclerView: RecyclerView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var reservationsRecyclerView: RecyclerView
    private lateinit var fabAddPet: ExtendedFloatingActionButton
    private lateinit var fabAddReservation: ExtendedFloatingActionButton
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var tvGreeting: TextView
    private lateinit var tvSubtitle: TextView
    private lateinit var tvPetsCounter: TextView
    private lateinit var tvReservationsCounter: TextView
    private lateinit var tvPetsEmpty: TextView
    private lateinit var tvReservationsEmpty: TextView
    private lateinit var etSearchProducts: TextInputEditText
    private lateinit var chipGroupCategories: ChipGroup
    private lateinit var btnGoToProfile: MaterialButton
    private lateinit var btnFindVet: MaterialButton
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var currentEmail: String? = null
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy · HH:mm", Locale.getDefault())
    private var currentProducts: List<Product> = emptyList()
    private var selectedCategory: String? = null
    private var currentSearchQuery: String = ""
    private var renderedCategories: List<String> = emptyList()
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            openNearestVet()
        } else {
            Toast.makeText(this, R.string.location_permission_required, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        currentEmail = intent.getStringExtra("USER_EMAIL")
        if (currentEmail.isNullOrBlank()) {
            Toast.makeText(this, "No se recibió el correo del usuario", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initViews()
        setupToolbar()
        setupRecyclerViews()
        setupActions()
        observeUiState()

        viewModel.loadDashboard(currentEmail!!)
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        petsRecyclerView = findViewById(R.id.rvPets)
        productsRecyclerView = findViewById(R.id.rvProducts)
        reservationsRecyclerView = findViewById(R.id.rvReservations)
        fabAddPet = findViewById(R.id.fabAddPet)
        fabAddReservation = findViewById(R.id.fabAddReservation)
        progressIndicator = findViewById(R.id.progressIndicator)
        tvGreeting = findViewById(R.id.tvDashboardGreeting)
        tvSubtitle = findViewById(R.id.tvDashboardSubtitle)
        tvPetsCounter = findViewById(R.id.tvPetsCounter)
        tvReservationsCounter = findViewById(R.id.tvReservationsCounter)
        tvPetsEmpty = findViewById(R.id.tvPetsEmpty)
        tvReservationsEmpty = findViewById(R.id.tvReservationsEmpty)
        etSearchProducts = findViewById(R.id.etSearchProducts)
        chipGroupCategories = findViewById(R.id.chipGroupCategories)
        btnGoToProfile = findViewById(R.id.btnGoToProfile)
        btnFindVet = findViewById(R.id.btnFindVet)

        petsAdapter = PetAdapter(::showPetDetailBottomSheet)
        productsAdapter = ProductAdapter()
        reservationsAdapter = ReservationAdapter()

        chipGroupCategories.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isEmpty()) {
                selectedCategory = null
            } else {
                val chip = group.findViewById<Chip>(checkedIds[0])
                selectedCategory = chip.tag as? String
            }
            applyProductFilters()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu_dashboard)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_profile -> {
                    openProfile()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerViews() {
        petsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = petsAdapter
        }

        productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = productsAdapter
        }

        reservationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = reservationsAdapter
        }
    }

    private fun setupActions() {
        fabAddPet.setOnClickListener {
            showAddPetDialog()
        }

        fabAddReservation.setOnClickListener {
            showAddReservationDialog()
        }

        etSearchProducts.addTextChangedListener { text ->
            currentSearchQuery = text?.toString().orEmpty()
            applyProductFilters()
        }

        btnGoToProfile.setOnClickListener { openProfile() }
        btnFindVet.setOnClickListener { openNearestVet() }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    progressIndicator.isVisible = state.isLoading
                    petsAdapter.submitList(state.pets)
                    reservationsAdapter.submitList(state.reservations)
                    currentProducts = state.products
                    renderCategoryChips(state.products)
                    applyProductFilters()

                    tvGreeting.text = getString(R.string.dashboard_greeting)
                    tvSubtitle.text = getString(R.string.dashboard_subtitle)
                    tvPetsCounter.text = state.pets.size.toString()
                    tvReservationsCounter.text = state.reservations.size.toString()

                    tvPetsEmpty.isVisible = state.pets.isEmpty() && !state.isLoading
                    tvReservationsEmpty.isVisible = state.reservations.isEmpty() && !state.isLoading

                    state.errorMessage?.let {
                        Toast.makeText(this@DashboardActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderCategoryChips(products: List<Product>) {
        val categories = products.map { it.category }.distinct()
        if (categories == renderedCategories && chipGroupCategories.childCount > 0) return
        renderedCategories = categories
        chipGroupCategories.removeAllViews()

        val allChip = Chip(this).apply {
            text = getString(R.string.chip_all)
            isCheckable = true
            tag = null
        }
        chipGroupCategories.addView(allChip)

        categories.forEach { category ->
            val chip = Chip(this).apply {
                text = category
                isCheckable = true
                tag = category
            }
            chipGroupCategories.addView(chip)
        }

        // Ensure first chip selected when no category chosen
        if (selectedCategory == null && chipGroupCategories.childCount > 0) {
            chipGroupCategories.check(chipGroupCategories.getChildAt(0).id)
        } else {
            // Try to find chip with tag = selectedCategory
            for (i in 0 until chipGroupCategories.childCount) {
                val chip = chipGroupCategories.getChildAt(i) as Chip
                if (chip.tag == selectedCategory) {
                    chipGroupCategories.check(chip.id)
                    break
                }
            }
        }
    }

    private fun applyProductFilters() {
        val query = currentSearchQuery.lowercase(Locale.getDefault())
        val filtered = currentProducts.filter { product ->
            val matchesCategory = selectedCategory.isNullOrBlank() || product.category.equals(selectedCategory, ignoreCase = true)
            val matchesQuery = query.isBlank() || product.title.lowercase(Locale.getDefault()).contains(query)
            matchesCategory && matchesQuery
        }
        productsAdapter.submitList(filtered)
    }

    private fun showAddPetDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_pet, null)
        val tilName = dialogView.findViewById<TextInputLayout>(R.id.tilPetName)
        val tilBreed = dialogView.findViewById<TextInputLayout>(R.id.tilPetBreed)
        val tilAge = dialogView.findViewById<TextInputLayout>(R.id.tilPetAge)
        val tilWeight = dialogView.findViewById<TextInputLayout>(R.id.tilPetWeight)

        val etName = dialogView.findViewById<TextInputEditText>(R.id.etPetName)
        val etBreed = dialogView.findViewById<TextInputEditText>(R.id.etPetBreed)
        val etAge = dialogView.findViewById<TextInputEditText>(R.id.etPetAge)
        val etWeight = dialogView.findViewById<TextInputEditText>(R.id.etPetWeight)

        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_add_pet_title)
            .setView(dialogView)
            .setPositiveButton(R.string.action_save, null)
            .setNegativeButton(R.string.action_cancel, null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val name = etName.text?.toString()?.trim().orEmpty()
                val breed = etBreed.text?.toString()?.trim().orEmpty()
                val age = etAge.text?.toString()?.toIntOrNull()
                val weight = etWeight.text?.toString()?.toDoubleOrNull()

                var hasErrors = false

                if (name.length < 2) {
                    tilName.error = "El nombre debe tener al menos 2 caracteres"
                    hasErrors = true
                } else {
                    tilName.error = null
                }

                if (breed.length < 2) {
                    tilBreed.error = "Debes indicar la raza"
                    hasErrors = true
                } else {
                    tilBreed.error = null
                }

                if (age == null || age <= 0) {
                    tilAge.error = "Ingresa una edad válida"
                    hasErrors = true
                } else {
                    tilAge.error = null
                }

                if (weight == null || weight <= 0) {
                    tilWeight.error = "Ingresa un peso válido"
                    hasErrors = true
                } else {
                    tilWeight.error = null
                }

                if (hasErrors) return@setOnClickListener

                val pet = Pet(
                    ownerEmail = currentEmail ?: "",
                    name = name,
                    breed = breed,
                    age = age ?: 0,
                    weight = weight ?: 0.0,
                    lastVisit = dateFormatter.format(System.currentTimeMillis()),
                    photoUri = null
                )
                viewModel.addPet(pet)
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun showPetDetailBottomSheet(pet: Pet) {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_pet_detail, null)
        view.findViewById<TextView>(R.id.tvPetDetailTitle).text = getString(R.string.pet_detail_title, pet.name)
        view.findViewById<TextView>(R.id.tvPetDetailBreed).text = getString(R.string.pet_detail_breed, pet.breed)
        view.findViewById<TextView>(R.id.tvPetDetailAge).text = getString(R.string.pet_detail_age, pet.age)
        view.findViewById<TextView>(R.id.tvPetDetailWeight).text = getString(R.string.pet_detail_weight, pet.weight)
        view.findViewById<TextView>(R.id.tvPetDetailLastVisit).text = getString(
            R.string.pet_detail_last_visit,
            pet.lastVisit
        )
        dialog.setContentView(view)
        dialog.show()
    }

    private fun showAddReservationDialog() {
        val state = viewModel.uiState.value
        if (state.pets.isEmpty()) {
            Toast.makeText(this, "Registra una mascota antes de agendar", Toast.LENGTH_SHORT).show()
            return
        }

        if (state.services.isEmpty()) {
            Toast.makeText(this, "Aún no hay servicios disponibles", Toast.LENGTH_SHORT).show()
            return
        }

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_reservation, null)
        val tilPet = dialogView.findViewById<TextInputLayout>(R.id.tilPetSelector)
        val tilService = dialogView.findViewById<TextInputLayout>(R.id.tilServiceSelector)
        val tilDate = dialogView.findViewById<TextInputLayout>(R.id.tilDate)
        val actvPet = dialogView.findViewById<MaterialAutoCompleteTextView>(R.id.actvPet)
        val actvService = dialogView.findViewById<MaterialAutoCompleteTextView>(R.id.actvService)
        val etDate = dialogView.findViewById<TextInputEditText>(R.id.etDate)

        actvPet.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, state.pets.map { it.name }))
        actvService.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, state.services.map { it.title }))

        val calendar = Calendar.getInstance()
        etDate.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    TimePickerDialog(
                        this,
                        { _, hourOfDay, minute ->
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            calendar.set(Calendar.MINUTE, minute)
                            etDate.setText(dateFormatter.format(calendar.time))
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_add_reservation_title)
            .setView(dialogView)
            .setPositiveButton(R.string.action_save, null)
            .setNegativeButton(R.string.action_cancel, null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val petName = actvPet.text?.toString()
                val serviceName = actvService.text?.toString()
                val dateString = etDate.text?.toString()

                var hasErrors = false

                if (petName.isNullOrBlank()) {
                    tilPet.error = "Selecciona una mascota"
                    hasErrors = true
                } else {
                    tilPet.error = null
                }

                if (serviceName.isNullOrBlank()) {
                    tilService.error = "Selecciona un servicio"
                    hasErrors = true
                } else {
                    tilService.error = null
                }

                if (dateString.isNullOrBlank()) {
                    tilDate.error = "Elige una fecha"
                    hasErrors = true
                } else {
                    tilDate.error = null
                }

                if (hasErrors) return@setOnClickListener

                val reservation = Reservation(
                    ownerEmail = currentEmail ?: "",
                    petName = petName.orEmpty(),
                    serviceName = serviceName.orEmpty(),
                    appointmentDate = calendar.timeInMillis,
                    status = "Confirmada"
                )
                viewModel.addReservation(reservation)
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun openProfile() {
        currentEmail?.let { email ->
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("USER_EMAIL", email)
            }
            startActivity(intent)
        }
    }

    private fun openNearestVet() {
        if (!hasLocationPermission()) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val uri = Uri.parse("geo:${location.latitude},${location.longitude}?q=veterinaria")
                    launchMapsIntent(uri)
                } else {
                    openMapsSearchFallback()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, R.string.location_unavailable, Toast.LENGTH_SHORT).show()
            }
    }

    private fun openMapsSearchFallback() {
        val uri = Uri.parse("geo:0,0?q=veterinaria%20para%20perros")
        launchMapsIntent(uri)
    }

    private fun launchMapsIntent(uri: Uri) {
        val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            `package` = "com.google.android.apps.maps"
        }
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            mapIntent.`package` = null
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, R.string.location_unavailable, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}


