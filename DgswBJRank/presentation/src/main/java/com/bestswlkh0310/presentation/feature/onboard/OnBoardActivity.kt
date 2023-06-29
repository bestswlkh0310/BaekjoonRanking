package com.bestswlkh0310.presentation.feature.onboard

import android.content.Intent
import androidx.activity.viewModels
import com.bestswlkh0310.presentation.base.BaseActivity
import com.bestswlkh0310.presentation.databinding.ActivityOnBoardBinding
import com.bestswlkh0310.presentation.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardActivity: BaseActivity<ActivityOnBoardBinding, OnBoardViewModel>() {
    override val viewModel: OnBoardViewModel by viewModels()

    override fun observerViewModel() {

    }

    fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}