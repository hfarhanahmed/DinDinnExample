package com.sevenpeakssoftware.farhan.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.sevenpeakssoftware.farhan.databinding.SplashLayoutBinding
import com.sevenpeakssoftware.farhan.ui.base.BaseActivity
import com.sevenpeakssoftware.farhan.ui.component.login.LoginActivity
import com.sevenpeakssoftware.farhan.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by FarhanAhmed
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity(){

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
