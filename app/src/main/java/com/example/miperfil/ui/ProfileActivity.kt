package com.example.miperfil.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.miperfil.R
import com.example.miperfil.data.local.AppDatabase
import com.example.miperfil.data.model.User
import com.example.miperfil.repository.UserRepository
import com.example.miperfil.utils.ValidationUtils
import com.example.miperfil.viewmodel.UserViewModel
import com.example.miperfil.viewmodel.UserViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.button.MaterialButton
import androidx.core.view.isVisible
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {
    
    private lateinit var ivProfile: ImageView
    private lateinit var etEmail: TextInputEditText
    private lateinit var etName: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilName: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var btnSave: MaterialButton
    private lateinit var btnCamera: MaterialButton
    private lateinit var btnGallery: MaterialButton
    private lateinit var progressBar: View
    
    private var currentUser: User? = null
    private var profileImageUri: Uri? = null
    
    private val viewModel: UserViewModel by viewModels {
        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        UserViewModelFactory(repository)
    }
    
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            profileImageUri?.let { uri ->
                displayImage(uri)
                updateProfileImageUri(uri.toString())
            }
        }
    }
    
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.data?.let { uri ->
                profileImageUri = uri
                displayImage(uri)
                updateProfileImageUri(uri.toString())
            }
        }
    }
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.CAMERA, false) -> {
                openCamera()
            }
            permissions.getOrDefault(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }, false
            ) -> {
                openGallery()
            }
            else -> {
                Toast.makeText(
                    this,
                    "Se requiere permiso para acceder a la cámara o galería",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        
        initViews()
        loadUserData()
        setupUI()
        setupObservers()
        setupValidation()
    }
    
    private fun initViews() {
        ivProfile = findViewById(R.id.ivProfile)
        etEmail = findViewById(R.id.etEmail)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        tilEmail = findViewById(R.id.tilEmail)
        tilName = findViewById(R.id.tilName)
        tilPhone = findViewById(R.id.tilPhone)
        btnSave = findViewById(R.id.btnSave)
        btnCamera = findViewById(R.id.btnCamera)
        btnGallery = findViewById(R.id.btnGallery)
        progressBar = findViewById(R.id.progressBar)
    }
    
    private fun loadUserData() {
        val email = intent.getStringExtra("USER_EMAIL") ?: return
        viewModel.loadUser(email)
    }
    
    private fun setupUI() {
        btnCamera.setOnClickListener {
            checkCameraPermission()
        }
        
        btnGallery.setOnClickListener {
            checkStoragePermission()
        }
        
        btnSave.setOnClickListener {
            attemptSave()
        }
    }
    
    private fun setupObservers() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.isVisible = isLoading
            btnSave.isEnabled = !isLoading
        })
        
        viewModel.currentUser.observe(this, Observer { user ->
            user?.let {
                currentUser = it
                displayUserData(it)
            }
        })
        
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        
        viewModel.saveSuccess.observe(this, Observer { success ->
            if (success) {
                Toast.makeText(this, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
        })
    }
    
    private fun setupValidation() {
        etName.addTextChangedListener { text ->
            val name = text?.toString() ?: ""
            val error = ValidationUtils.getNameError(name)
            tilName.error = error
            tilName.isErrorEnabled = error != null
        }
        
        etPhone.addTextChangedListener { text ->
            val phone = text?.toString() ?: ""
            val error = ValidationUtils.getPhoneError(phone)
            tilPhone.error = error
            tilPhone.isErrorEnabled = error != null
        }
    }
    
    private fun displayUserData(user: User) {
        etEmail.setText(user.email)
        etName.setText(user.name)
        etPhone.setText(user.phone)
        
        user.profileImageUri?.let { uriString ->
            val uri = Uri.parse(uriString)
            displayImage(uri)
            profileImageUri = uri
        }
    }
    
    private fun displayImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .placeholder(R.drawable.ic_profile_placeholder)
            .error(R.drawable.ic_profile_placeholder)
            .circleCrop()
            .into(ivProfile)
    }
    
    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }
            else -> {
                requestPermissionLauncher.launch(arrayOf(Manifest.permission.CAMERA))
            }
        }
    }
    
    private fun checkStoragePermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                openGallery()
            }
            else -> {
                requestPermissionLauncher.launch(arrayOf(permission))
            }
        }
    }
    
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile = createImageFile()
        
        photoFile?.let { file ->
            profileImageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    this,
                    "${packageName}.fileprovider",
                    file
                )
            } else {
                Uri.fromFile(file)
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, profileImageUri)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            
            if (intent.resolveActivity(packageManager) != null) {
                cameraLauncher.launch(intent)
            } else {
                Toast.makeText(this, "No se puede abrir la cámara", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Error al crear archivo de imagen", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        
        if (intent.resolveActivity(packageManager) != null) {
            galleryLauncher.launch(intent)
        } else {
            Toast.makeText(this, "No se puede abrir la galería", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir("images")
        
        return try {
            File.createTempFile(imageFileName, ".jpg", storageDir)
        } catch (e: Exception) {
            null
        }
    }
    
    private fun updateProfileImageUri(uriString: String) {
        currentUser?.let { user ->
            val updatedUser = user.copy(profileImageUri = uriString)
            currentUser = updatedUser
        }
    }
    
    private fun attemptSave() {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        
        val nameError = ValidationUtils.getNameError(name)
        val phoneError = ValidationUtils.getPhoneError(phone)
        
        if (nameError != null || phoneError != null) {
            tilName.error = nameError
            tilName.isErrorEnabled = nameError != null
            tilPhone.error = phoneError
            tilPhone.isErrorEnabled = phoneError != null
            Toast.makeText(this, "Por favor, corrige los errores", Toast.LENGTH_SHORT).show()
            return
        }
        
        currentUser?.let { user ->
            val updatedUser = user.copy(
                name = name,
                phone = phone,
                profileImageUri = profileImageUri?.toString()
            )
            viewModel.updateUserProfile(updatedUser)
        }
    }
}

