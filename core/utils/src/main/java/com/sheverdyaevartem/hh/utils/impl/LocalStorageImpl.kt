package com.sheverdyaevartem.hh.utils.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.sheverdyaevartem.hh.utils.local_storage.LocalStorage

class LocalStorageImpl(private val sharedPreferences: SharedPreferences) : LocalStorage {

    companion object {
        private const val SHARED_ID_KEY = "id key"
    }

    override fun safeId(id: String) {
        sharedPreferences.edit {
            putString(SHARED_ID_KEY, id)
        }
    }

    override fun getId(): String {
        return sharedPreferences.getString(SHARED_ID_KEY, "").toString()
    }
}