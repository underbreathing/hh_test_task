package com.sheverdyaevartem.hh.sign_in.domain.model


sealed interface Resource<T> {
    data class Answer<T>(val data: T) : Resource<T>
    class InternetError<T> : Resource<T>

    class ServerError<T> : Resource<T>

    class RequestError<T> : Resource<T>
}