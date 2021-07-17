package com.example.cardvisit.api

import com.example.cardvisit.data.model.remote.RemoteCard
import retrofit2.http.GET

interface ApiService {
    @GET("cards")
    suspend fun getCards(): List<RemoteCard>
}