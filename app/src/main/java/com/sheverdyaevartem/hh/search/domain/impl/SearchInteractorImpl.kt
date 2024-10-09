package com.sheverdyaevartem.hh.search.domain.impl

import com.sheverdyaevartem.hh.search.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.search.domain.mappers.OfferMapper
import com.sheverdyaevartem.hh.search.domain.mappers.VacancyMapper
import com.sheverdyaevartem.hh.search.domain.model.Resource
import com.sheverdyaevartem.hh.search.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo
import com.sheverdyaevartem.hh.search.ui.model.OffersVacanciesInfo
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