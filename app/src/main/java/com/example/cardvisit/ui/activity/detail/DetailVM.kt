package com.example.cardvisit.ui.activity.detail

import androidx.lifecycle.ViewModel
import com.example.cardvisit.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(): ViewModel() {
    @Inject lateinit var logUtil: LogUtil

}