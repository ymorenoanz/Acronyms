package com.yaritzama.acronyms.utils

sealed class ResponseStatus<T>(
    val data: T? = null,
    val message: String? = null
){
    data class Success<T>(private val successData: T?): ResponseStatus<T>(successData)
    data class Error<T>(private val errorMessage: String?,
                        private val errorData: T? = null): ResponseStatus<T>(errorData, errorMessage)
    class Loading<T> : ResponseStatus<T>()
}

