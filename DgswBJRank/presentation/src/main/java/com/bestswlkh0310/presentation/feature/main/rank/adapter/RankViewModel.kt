package com.bestswlkh0310.presentation.feature.main.rank.adapter

import com.bestswlkh0310.presentation.base.BaseViewModel
import com.bestswlkh0310.domain.repository.AuthRepository
import com.bestswlkh0310.domain.repository.GrassesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val grassesRepository: GrassesRepository,
    private val authRepository: AuthRepository
): BaseViewModel() {

}