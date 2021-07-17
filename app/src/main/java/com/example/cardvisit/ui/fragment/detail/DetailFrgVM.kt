package com.example.cardvisit.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.example.cardvisit.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFrgVM @Inject constructor(): ViewModel() {
    @Inject lateinit var logUtil: LogUtil

}