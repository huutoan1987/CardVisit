package com.example.cardvisit.util

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogUtil @Inject constructor(){
    fun d(content: String) {
        Log.d("CardVisit", content)
    }
}