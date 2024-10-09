package com.sheverdyaevartem.hh.search.ui.view_model.states

import com.sheverdyaevartem.hh.search.ui.fragment.rv.OfferVacancyRVItems

sealed interface InitDataState {

    data object IsLoading : InitDataState

    data class Content(val data: OfferVacancyRVItems) : InitDataState

    data object ServerError : InitDataState

    data object ConnectionError : InitDataState

    data object InternalError : InitDataState
}