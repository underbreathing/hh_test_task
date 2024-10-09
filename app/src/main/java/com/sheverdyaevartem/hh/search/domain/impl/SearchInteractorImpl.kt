package com.sheverdyaevartem.hh.search.domain.impl

import com.sheverdyaevartem.hh.search.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.search.domain.mappers.OfferMapper
import com.sheverdyaevartem.hh.search.domain.model.Resource
import com.sheverdyaevartem.hh.search.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchInteractorImpl(
    private val searchNetworkRepository: SearchNetworkRepository,
    private val offerMapper: OfferMapper
) : SearchInteractor {
    override fun getInitData(id: String): Flow<Resource<List<OfferInfo>>> {
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
                    Resource.Success(it.data.map(offerMapper::map))
                }
            }
        }


    }
}