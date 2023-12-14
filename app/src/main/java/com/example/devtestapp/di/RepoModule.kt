package com.example.devtestapp.di

import com.example.devtestapp.data.repository.ContactsRepositoryImpl
import com.example.devtestapp.domain.repository.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindContactsRepository(contactsRepositoryImpl: ContactsRepositoryImpl) : ContactsRepository
}