package com.example.cardvisit.repo

import com.example.cardvisit.api.ApiService
import com.example.cardvisit.data.CardVisitDB
import com.example.cardvisit.data.entity.Card
import com.example.cardvisit.util.LogUtil
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepo @Inject constructor() {
    @Inject lateinit var apiService: ApiService
    @Inject lateinit var db: CardVisitDB
    @Inject lateinit var logUtil: LogUtil

    fun getAllCardInDbAsFlow() = db.getCardDao().getAllCardAsFlow().catch { e ->
        logUtil.d(e.localizedMessage)
        emit(emptyList())
    }

    fun getRemoteCardAsFlow() = flow {
        emit(emptyList())
        emit(apiService.getCards().map { remoteCard -> remoteCard.toCard() })
    }.catch { e ->
        logUtil.d(e.localizedMessage)
        emit(emptyList())
    }

    suspend fun createNewCard(card: Card) = db.getCardDao().insert(card)
}