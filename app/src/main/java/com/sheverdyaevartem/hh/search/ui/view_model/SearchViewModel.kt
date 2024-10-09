package com.sheverdyaevartem.hh.search.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.hh.search.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.search.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.search.domain.model.Resource
import com.sheverdyaevartem.hh.search.ui.fragment.rv.OfferRVItem
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo
import com.sheverdyaevartem.hh.search.ui.view_model.states.InitDataState
import kotlinx.coroutines.launch

class SearchViewModel(
    id: String,
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val _initDataLiveData: MutableLiveData<InitDataState> = MutableLiveData()

    val initData: LiveData<InitDataState> = _initDataLiveData

    init {
        fillData(id)
    }

    private fun fillData(id: String) {
        viewModelScope.launch {
            _initDataLiveData.value = InitDataState.IsLoading
            searchInteractor.getInitData(id).collect { data ->
                when (data) {
                    is Resource.InternetError -> _initDataLiveData.value =
                        InitDataState.ConnectionError

                    is Resource.RequestError -> _initDataLiveData.value =
                        InitDataState.InternalError

                    is Resource.ServerError -> _initDataLiveData.value = InitDataState.ServerError
                    is Resource.Success -> {
                        if (data.data.isEmpty()) {
                            _initDataLiveData.value = InitDataState.Empty
                        } else {
                            _initDataLiveData.value =
                                InitDataState.Content(offerInfoToOfferRvItem(data.data))
                        }
                    }
                }
            }
        }
    }

    private fun offerInfoToOfferRvItem(data: List<OfferInfo>): List<OfferRVItem> {
        return data.map {
            OfferRVItem(it)
        }
    }
}