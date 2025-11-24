package com.example.miperfil.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import com.example.miperfil.R
import com.google.android.material.button.MaterialButton

class AnimatedButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {
    
    private var scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.button_scale)
    private var fadeAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
    
    init {
        fadeAnimation?.let { startAnimation(it) }
        setOnClickListener { view ->
            scaleAnimation?.let { view.startAnimation(it) }
        }
    }
    
    fun animateSuccess() {
        val successAnimation = AnimationUtils.loadAnimation(context, R.anim.button_success)
        startAnimation(successAnimation)
    }
    
    fun animateError() {
        val errorAnimation = AnimationUtils.loadAnimation(context, R.anim.button_error)
        startAnimation(errorAnimation)
    }
    
    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) 1.0f else 0.5f
    }
}

