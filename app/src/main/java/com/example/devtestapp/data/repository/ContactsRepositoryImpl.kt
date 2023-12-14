package com.example.devtestapp.data.repository

import com.example.devtestapp.data.api.ContactsApi
import com.example.devtestapp.data.mapper.ContactsResponseMapper.toContact
import com.example.devtestapp.domain.model.ContactsModel
import com.example.devtestapp.domain.repository.ContactsRepository
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(private val contactsApi: ContactsApi) :
    ContactsRepository {

    override suspend fun getContacts(): List<ContactsModel> {
        return contactsApi.getContacts().toContact()
    }
}