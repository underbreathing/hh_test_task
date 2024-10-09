package com.sheverdyaevartem.hh.search.ui.view_model.states

import com.sheverdyaevartem.hh.search.ui.fragment.rv.OfferRVItem
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo

sealed interface InitDataState {

    data object IsLoading : InitDataState

    data class Content(val data: List<OfferRVItem>) : InitDataState

    data object Empty : InitDataState

    data object ServerError : InitDataState

    data object ConnectionError : InitDataState

    data object InternalError : InitDataState
}