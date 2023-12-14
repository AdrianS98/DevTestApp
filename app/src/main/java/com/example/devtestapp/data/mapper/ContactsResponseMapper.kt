package com.example.devtestapp.data.mapper

import com.example.devtestapp.data.model.ListContactsResponse
import com.example.devtestapp.domain.model.ContactsModel


object ContactsResponseMapper {

    fun ListContactsResponse.toContact(): List<ContactsModel> {

        val contactModels = ArrayList<ContactsModel>()

        listContactsResponse?.forEach { response ->
            contactModels.add(
                ContactsModel(
                    "${response.name.first} ${response.name.last}",
                    response.email,
                    response.gender,
                    response.registered.date,
                    response.phone,
                    response.picture.large,
                    response.location.coordinates.latitude,
                    response.location.coordinates.longitude
                )
            )
        }
        return contactModels
    }
}