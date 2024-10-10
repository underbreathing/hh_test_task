package com.sheverdyaevartem.hh.impl.data.impl

import com.sheverdyaevartem.hh.feature_sign_in.api.domain.api.LocalRepository
import com.sheverdyaevartem.hh.utils.local_storage.LocalStorage

class LocalRepositoryImpl(private val localStorage: LocalStorage) : LocalRepository {
    override fun safeId(id: String) {
        localStorage.safeId(id)
    }
}