package com.example.devtestapp.data.api

import com.example.devtestapp.data.model.ListContactsResponse
import com.example.devtestapp.utils.Constants
import retrofit2.http.GET

interface ContactsApi {
    @GET(Constants.ENDPOINT_CONTACTS)
    suspend fun getContacts(): ListContactsResponse
}