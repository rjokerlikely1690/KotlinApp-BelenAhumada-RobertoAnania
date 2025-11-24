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
import com.example.miperfil.repository.UserRepository
import com.example.miperfil.utils.ValidationUtils
import com.example.miperfil.viewmodel.UserViewModel
import com.example.miperfil.viewmodel.UserViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.button.MaterialButton
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var btnLogin: MaterialButton
    private lateinit var btnRegister: MaterialButton
    private lateinit var progressBar: View
    
    private val viewModel: UserViewModel by viewModels {
        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        UserViewModelFactory(repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupUI()
        setupObservers()
        setupValidation()
    }
    
    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        progressBar = findViewById(R.id.progressBar)
    }
    
    private fun setupUI() {
        btnRegister.setOnClickListener {
            navigateToRegister()
        }
        
        btnLogin.setOnClickListener {
            attemptLogin()
        }
    }
    
    private fun setupObservers() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.isVisible = isLoading
            btnLogin.isEnabled = !isLoading
        })
        
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        
        viewModel.currentUser.observe(this, Observer { user ->
            user?.let {
                navigateToDashboard(it.email)
            }
        })
        
        viewModel.saveSuccess.observe(this, Observer { success ->
            if (success) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
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
    }
    
    private fun attemptLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString()
        
        val emailError = ValidationUtils.getEmailError(email)
        val passwordError = ValidationUtils.getPasswordError(password)
        
        if (emailError != null || passwordError != null) {
            tilEmail.error = emailError
            tilEmail.isErrorEnabled = emailError != null
            tilPassword.error = passwordError
            tilPassword.isErrorEnabled = passwordError != null
            Toast.makeText(this, "Por favor, corrige los errores en el formulario", Toast.LENGTH_SHORT).show()
            return
        }
        
        tilEmail.error = null
        tilEmail.isErrorEnabled = false
        tilPassword.error = null
        tilPassword.isErrorEnabled = false
        
        viewModel.login(email, password)
    }
    
    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    
    private fun navigateToDashboard(email: String) {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("USER_EMAIL", email)
        }
        startActivity(intent)
        finish()
    }
}

