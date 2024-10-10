package com.sheverdyaevartem.hh.feature_search.api.domain.impl

import com.sheverdyaevartem.hh.feature_search.api.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.feature_search.api.ui.mappers.OfferMapper
import com.sheverdyaevartem.hh.feature_search.api.ui.mappers.VacancyMapper
import com.sheverdyaevartem.hh.feature_search.api.domain.model.Resource
import com.sheverdyaevartem.hh.feature_search.api.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.feature_search.api.ui.model.OfferInfo
import com.sheverdyaevartem.hh.feature_search.api.ui.model.OffersVacanciesInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchInteractorImpl(
    private val searchNetworkRepository: SearchNetworkRepository,
    private val offerMapper: OfferMapper,
    private val vacancyMapper: VacancyMapper
) : SearchInteractor {
    override fun getInitData(id: String): Flow<Resource<OffersVacanciesInfo>> {
        return searchNetworkRepository.getInitData(id).map {
            when (it) {
                is Resource.InternetError -> {
                    Resource.InternetError()
                }

                is Resource.RequestError -> {
                    Resource.RequestError()
                }

                is Resource.ServerError -> {
                    Resource.ServerError()
                }

                is Resource.Success -> {
                    Resource.Success(
                        OffersVacanciesInfo(
                            it.data.offers?.map(offerMapper::map),
                            it.data.vacancies?.map(vacancyMapper::map)
                        )
                    )
                }
            }
        }


    }
}