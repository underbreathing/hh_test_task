package com.sheverdyaevartem.hh.search.domain.interactors

import com.sheverdyaevartem.hh.search.domain.model.Resource
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    fun getInitData(id: String): Flow<Resource<List<OfferInfo>>>
}