package com.sheverdyaevartem.hh.search.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.hh.search.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.search.domain.model.Resource
import com.sheverdyaevartem.hh.search.ui.fragment.rv.OfferRVItem
import com.sheverdyaevartem.hh.search.ui.fragment.rv.OfferVacancyRVItems
import com.sheverdyaevartem.hh.search.ui.fragment.rv.VacancyRVItem
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo
import com.sheverdyaevartem.hh.search.ui.model.OffersVacanciesInfo
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
                        _initDataLiveData.value =
                            InitDataState.Content(offersVacanciesToRVItems(data.data))
                    }
                }
            }
        }
    }

    private fun offersVacanciesToRVItems(data: OffersVacanciesInfo): OfferVacancyRVItems {
        var offerRVItems = emptyList<OfferRVItem>()
        var vacancyRVItems = emptyList<VacancyRVItem>()
        if (!data.offersInfo.isNullOrEmpty()) {
            offerRVItems = data.offersInfo.map {
                with(it) { OfferRVItem(buttonText, id, link, title) }
            }
        }
        if (!data.vacanciesInfo.isNullOrEmpty()) {
            vacancyRVItems = data.vacanciesInfo.map {
                with(it) {
                    VacancyRVItem(
                        addressDto,
                        appliedNumber,
                        company,
                        description,
                        experienceDto,
                        id,
                        isFavorite,
                        lookingNumber,
                        publishedDate,
                        questions,
                        responsibilities,
                        salaryDto,
                        schedules,
                        title
                    )
                }
            }
        }
        return OfferVacancyRVItems(offerRVItems, vacancyRVItems)
    }
}