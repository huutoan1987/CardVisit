package com.example.cardvisit.data.model.remote

import com.example.cardvisit.data.entity.Card
import com.google.gson.annotations.SerializedName

data class RemoteCard(
    @SerializedName("id")
    var remoteId: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("mobile")
    var mobile: String,

    @SerializedName("company")
    var company: String,

    @SerializedName("position")
    var position: String,

    @SerializedName("address")
    var address: String
) {
    fun toCard() = Card(
        localId = -1L,
        remoteId = remoteId,
        name = name,
        mobile = mobile,
        company = company,
        position = position,
        address = address,
        gender = null,
        dob = null,
        about = null
    )
}
