package com.sheverdyaevartem.hh.search.data.repository

import com.sheverdyaevartem.hh.search.data.remote_data_source.api.SearchRemoteDataSource
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.OffersVacanciesRequest
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.OffersVacanciesResponse
import com.sheverdyaevartem.hh.search.data.remote_data_source.mappers.OfferDtoMapper
import com.sheverdyaevartem.hh.search.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.search.domain.model.Offer
import com.sheverdyaevartem.hh.search.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchNetworkRepositoryImpl(
    private val searchRemoteDataSource: SearchRemoteDataSource, private val offerDtoMapper: OfferDtoMapper
) : SearchNetworkRepository {
    override fun getInitData(id: String): Flow<Resource<List<Offer>>> {
        return flow {
            val response = searchRemoteDataSource.doRequest(OffersVacanciesRequest(id))

            if (response is OffersVacanciesResponse) {
                emit(Resource.Success(response.offerDtos.map(offerDtoMapper::map)))
            } else {
                emit(processResultCode(response.resultCode))
            }
        }

    }

    private fun <T> processResultCode(resultCode: Int): Resource<T> {
        return when (resultCode) {
            -1 -> Resource.InternetError()
            400 -> Resource.RequestError()
            else -> Resource.ServerError()
        }
    }
}