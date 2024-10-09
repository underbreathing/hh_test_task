package com.sheverdyaevartem.hh.feature_search.api.domain.repository

import com.sheverdyaevartem.hh.feature_search.api.domain.model.OffersVacancies
import com.sheverdyaevartem.hh.feature_search.api.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface SearchNetworkRepository {


    fun getInitData(id: String): Flow<Resource<OffersVacancies>>
}