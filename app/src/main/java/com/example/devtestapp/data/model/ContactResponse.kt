package com.example.devtestapp.data.model

import com.example.devtestapp.data.model.items.Location
import com.example.devtestapp.data.model.items.Name
import com.example.devtestapp.data.model.items.Picture
import com.example.devtestapp.data.model.items.Registered
import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: Name,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("registered")
    val registered: Registered
)
