package com.sheverdyaevartem.hh.feature_search.impl.data.impl

import com.sheverdyaevartem.hh.feature_search.api.domain.repository.LocalRepository
import com.sheverdyaevartem.hh.utils.local_storage.LocalStorage

class LocalRepositoryImpl(private val localStorage: LocalStorage) : LocalRepository {
    override fun getId(): String {
        return localStorage.getId()
    }
}