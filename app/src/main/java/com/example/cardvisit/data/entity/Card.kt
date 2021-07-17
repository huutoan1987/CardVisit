package com.example.cardvisit.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Tbl_Card")
data class Card(
    @ColumnInfo(name = "localId")
    @PrimaryKey(autoGenerate = true)
    var localId: Long = 0,

    @ColumnInfo(name = "remoteId")
    var remoteId: Long = -1,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "mobile")
    var mobile: String?,

    @ColumnInfo(name = "company")
    var company: String?,

    @ColumnInfo(name = "position")
    var position: String?,

    @ColumnInfo(name = "address")
    var address: String?,

    @ColumnInfo(name = "gender")
    var gender: String?,

    @ColumnInfo(name = "dob")
    var dob: String?,

    @ColumnInfo(name = "about")
    var about: String?
) : Parcelable
