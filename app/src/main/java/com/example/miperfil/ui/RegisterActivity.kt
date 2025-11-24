package com.example.miperfil.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
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

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etName: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilName: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var btnRegister: MaterialButton
    private lateinit var progressBar: View
    
    private val viewModel: UserViewModel by viewModels {
        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        UserViewModelFactory(repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        
        initViews()
        setupUI()
        setupObservers()
        setupValidation()
    }
    
    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilName = findViewById(R.id.tilName)
        tilPhone = findViewById(R.id.tilPhone)
        btnRegister = findViewById(R.id.btnRegister)
        progressBar = findViewById(R.id.progressBar)
    }
    
    private fun setupUI() {
        btnRegister.setOnClickListener {
            attemptRegister()
        }
    }
    
    private fun setupObservers() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.isVisible = isLoading
            btnRegister.isEnabled = !isLoading
        })
        
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        
        viewModel.saveSuccess.observe(this, Observer { success ->
            if (success) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                viewModel.currentUser.value?.let { user ->
                    navigateToDashboard(user.email)
                }
            }
        })
    }
    
    private fun setupValidation() {
        etEmail.addTextChangedListener { text ->
            val email = text?.toString() ?: ""
            val error = ValidationUtils.getEmailError(email)
            tilEmail.error = error
            tilEmail.isErrorEnabled = error != null
        }
        
        etPassword.addTextChangedListener { text ->
            val password = text?.toString() ?: ""
            val error = ValidationUtils.getPasswordError(password)
            tilPassword.error = error
            tilPassword.isErrorEnabled = error != null
        }
        
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
    
    private fun attemptRegister() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString()
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        
        val emailError = ValidationUtils.getEmailError(email)
        val passwordError = ValidationUtils.getPasswordError(password)
        val nameError = ValidationUtils.getNameError(name)
        val phoneError = ValidationUtils.getPhoneError(phone)
        
        var hasErrors = false
        
        if (emailError != null) {
            tilEmail.error = emailError
            tilEmail.isErrorEnabled = true
            hasErrors = true
        } else {
            tilEmail.error = null
            tilEmail.isErrorEnabled = false
        }
        
        if (passwordError != null) {
            tilPassword.error = passwordError
            tilPassword.isErrorEnabled = true
            hasErrors = true
        } else {
            tilPassword.error = null
            tilPassword.isErrorEnabled = false
        }
        
        if (nameError != null) {
            tilName.error = nameError
            tilName.isErrorEnabled = true
            hasErrors = true
        } else {
            tilName.error = null
            tilName.isErrorEnabled = false
        }
        
        if (phoneError != null) {
            tilPhone.error = phoneError
            tilPhone.isErrorEnabled = true
            hasErrors = true
        } else {
            tilPhone.error = null
            tilPhone.isErrorEnabled = false
        }
        
        if (hasErrors) {
            Toast.makeText(this, "Por favor, corrige los errores en el formulario", Toast.LENGTH_SHORT).show()
            return
        }
        
        viewModel.loadUser(email)
        viewModel.currentUser.observe(this, Observer { existingUser ->
            if (existingUser != null) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                val newUser = User(
                    email = email,
                    name = name,
                    password = password,
                    phone = phone
                )
                viewModel.registerUser(newUser)
            }
        })
    }
    
    private fun navigateToDashboard(email: String) {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("USER_EMAIL", email)
        }
        startActivity(intent)
        finish()
    }
}

