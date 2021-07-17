package com.example.cardvisit.di

import android.app.Application
import androidx.room.Room
import com.example.cardvisit.data.CardVisitDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {
    @Provides
    @Singleton
    fun provideDB(app: Application): CardVisitDB {
        return Room.databaseBuilder(app, CardVisitDB::class.java, "CardVisitDB")
            .build()
    }
}