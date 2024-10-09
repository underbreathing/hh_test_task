package com.sheverdyaevartem.hh.search.domain.model


sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    class InternetError<T> : Resource<T>

    class ServerError<T> : Resource<T>

    class RequestError<T> : Resource<T>
}