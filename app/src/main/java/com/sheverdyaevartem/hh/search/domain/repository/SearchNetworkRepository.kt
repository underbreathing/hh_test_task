package com.sheverdyaevartem.hh.search.domain.repository

import com.sheverdyaevartem.hh.search.domain.model.Offer
import com.sheverdyaevartem.hh.search.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface SearchNetworkRepository {


    fun getInitData(id: String): Flow<Resource<List<Offer>>>
}