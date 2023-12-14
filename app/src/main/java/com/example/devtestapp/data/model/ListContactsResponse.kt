package com.example.devtestapp.data.model

import com.google.gson.annotations.SerializedName

data class ListContactsResponse(
    @SerializedName("results")
    val listContactsResponse: List<ContactResponse>? = null
)