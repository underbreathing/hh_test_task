package com.sheverdyaevartem.hh.feature_search.api.ui.view_model.states

import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.OfferVacancyRVItems

sealed interface InitDataState {

    data object IsLoading : InitDataState

    data class Content(val data: OfferVacancyRVItems, val countOfFavorites: Int) : InitDataState

    data object ServerError : InitDataState

    data object ConnectionError : InitDataState

    data object InternalError : InitDataState
}