package com.example.devtestapp.domain.repository

import com.example.devtestapp.domain.model.ContactsModel

interface ContactsRepository {
    suspend fun getContacts(): List<ContactsModel>
}