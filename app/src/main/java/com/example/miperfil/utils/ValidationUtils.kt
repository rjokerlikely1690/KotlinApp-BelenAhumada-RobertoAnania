package com.example.miperfil.utils

import java.util.regex.Pattern

object ValidationUtils {
    private val EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    
    private val PHONE_PATTERN = Pattern.compile("^[0-9]{10}$")
    
    fun isValidEmail(email: String?): Boolean {
        if (email.isNullOrBlank()) return false
        return EMAIL_PATTERN.matcher(email).matches()
    }
    
    fun isValidPassword(password: String?): Boolean {
        if (password.isNullOrBlank()) return false
        return password.length >= 6
    }
    
    fun isValidName(name: String?): Boolean {
        return !name.isNullOrBlank() && name.trim().length >= 2
    }
    
    fun isValidPhone(phone: String?): Boolean {
        if (phone.isNullOrBlank()) return false
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        return PHONE_PATTERN.matcher(cleanPhone).matches()
    }
    
    fun getEmailError(email: String?): String? {
        return when {
            email.isNullOrBlank() -> "El correo electrónico es requerido"
            !isValidEmail(email) -> "Correo electrónico inválido"
            else -> null
        }
    }
    
    fun getPasswordError(password: String?): String? {
        return when {
            password.isNullOrBlank() -> "La contraseña es requerida"
            !isValidPassword(password) -> "La contraseña debe tener al menos 6 caracteres"
            else -> null
        }
    }
    
    fun getNameError(name: String?): String? {
        return when {
            name.isNullOrBlank() -> "El nombre es requerido"
            !isValidName(name) -> "El nombre debe tener al menos 2 caracteres"
            else -> null
        }
    }
    
    fun getPhoneError(phone: String?): String? {
        return when {
            phone.isNullOrBlank() -> "El teléfono es requerido"
            !isValidPhone(phone) -> "Teléfono inválido (debe tener 10 dígitos)"
            else -> null
        }
    }
}

