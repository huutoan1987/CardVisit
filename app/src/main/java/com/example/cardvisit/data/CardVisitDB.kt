package com.example.cardvisit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cardvisit.data.dao.CardDAO
import com.example.cardvisit.data.entity.Card

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardVisitDB : RoomDatabase() {
    abstract fun getCardDao(): CardDAO
}