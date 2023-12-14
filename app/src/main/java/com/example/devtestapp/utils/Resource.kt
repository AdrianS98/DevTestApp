package com.example.devtestapp.utils

import androidx.annotation.StringRes

sealed class Resource<T>(val data: T? = null, val message: Int? = null) {
    class Succes<T>(data: T?): Resource<T>(data)
    class Error<T>(@StringRes message: Int?, data: T? = null): Resource<T>(data,message)
    class Loading<T> : Resource<T>()
}