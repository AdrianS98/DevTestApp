package com.example.devtestapp.presentation.contactsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devtestapp.R
import com.example.devtestapp.domain.model.ContactsModel
import com.example.devtestapp.domain.usecase.GetContactsUseCase
import com.example.devtestapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {

    private val _contacts = MutableLiveData<Resource<List<ContactsModel>>>()

    val contacts: LiveData<Resource<List<ContactsModel>>>
        get() = _contacts

    fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {

            _contacts.postValue(Resource.Loading())
            try {
                val result = getContactsUseCase.getContacts()
                _contacts.postValue(Resource.Succes(result))
            } catch (e: Exception) {
                _contacts.postValue(Resource.Error(ErrorHandler.getMessage(e)))
            }
        }
    }

    object ErrorHandler {
        fun getMessage(exception: Exception): Int {
            return when (exception) {
                is HttpException -> getHttpError(exception)
                else -> generalError()
            }
        }

        private fun getHttpError(exception: HttpException): Int {
            return when (exception.code()) {
                404 -> R.string.eror_not_found
                else -> generalError()
            }
        }

        private fun generalError() = R.string.error_something_went_wrong
    }
}