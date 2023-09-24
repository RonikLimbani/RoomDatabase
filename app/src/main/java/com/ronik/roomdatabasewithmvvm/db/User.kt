package com.ronik.roomdatabasewithmvvm.db

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Email") val email: String,
    @ColumnInfo(name = "PhoneNumber") val phoneNumber: String,
): Parcelable
