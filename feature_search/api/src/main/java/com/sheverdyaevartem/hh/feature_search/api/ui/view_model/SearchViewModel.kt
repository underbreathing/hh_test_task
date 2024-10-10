package com.sheverdyaevartem.hh.feature_search.api.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.hh.feature_search.api.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.feature_search.api.domain.model.Resource
import com.sheverdyaevartem.hh.feature_search.api.domain.repository.LocalRepository
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.OfferRVItem
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.OfferVacancyRVItems
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.VacancyRVItem
import com.sheverdyaevartem.hh.feature_search.api.ui.model.OffersVacanciesInfo
import com.sheverdyaevartem.hh.feature_search.api.ui.model.VacancyInfo
import com.sheverdyaevartem.hh.feature_search.api.ui.view_model.states.InitDataState
import kotlinx.coroutines.launch

class SearchViewModel(
    localRepository: LocalRepository,
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val _initDataLiveData: MutableLiveData<InitDataState> = MutableLiveData()

    val initData: LiveData<InitDataState> = _initDataLiveData

    init {
        fillData(localRepository.getId())
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
                            InitDataState.Content(
                                offersVacanciesToRVItems(data.data),
                                calculateFavoritesCount(data.data.vacanciesInfo)
                            )
                    }
                }
            }
        }
    }

    private fun calculateFavoritesCount(vacanciesInfo: List<VacancyInfo>?): Int {
        return vacanciesInfo?.count {
            it.isFavorite
        } ?: 0
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