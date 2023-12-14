package com.example.devtestapp.domain.usecase

import com.example.devtestapp.domain.model.ContactsModel
import com.example.devtestapp.domain.repository.ContactsRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) {

    suspend fun getContacts(): List<ContactsModel> {
        return contactsRepository.getContacts()
    }
}