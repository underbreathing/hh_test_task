package com.sheverdyaevartem.hh.utils.local_storage

interface LocalStorage {

    fun safeId(id: String)

    fun getId(): String
}