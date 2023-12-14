package com.example.devtestapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactsModel(
    val name: String,
    val email: String,
    val gender: String,
    val registeredDate: String,
    val phone: String,
    val pictureUrl: String,
    val latitude: String,
    val longitude: String

) : Parcelable
