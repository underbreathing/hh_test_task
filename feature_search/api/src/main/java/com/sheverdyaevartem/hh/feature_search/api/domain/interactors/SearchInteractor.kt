package com.sheverdyaevartem.hh.feature_search.api.domain.interactors

import com.sheverdyaevartem.hh.feature_search.api.domain.model.Resource
import com.sheverdyaevartem.hh.feature_search.api.ui.model.OffersVacanciesInfo
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    fun getInitData(id: String): Flow<Resource<OffersVacanciesInfo>>
}