package com.sheverdyaevartem.hh.search.data.repository

import com.sheverdyaevartem.hh.search.data.remote_data_source.api.SearchRemoteDataSource
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.OffersVacanciesRequest
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.OffersVacanciesResponse
import com.sheverdyaevartem.hh.search.data.remote_data_source.mappers.OfferDtoMapper
import com.sheverdyaevartem.hh.search.data.remote_data_source.mappers.VacancyDtoMapper
import com.sheverdyaevartem.hh.search.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.search.domain.model.OffersVacancies
import com.sheverdyaevartem.hh.search.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchNetworkRepositoryImpl(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val offerDtoMapper: OfferDtoMapper,
    private val vacancyDtoMapper: VacancyDtoMapper
) : SearchNetworkRepository {
    override fun getInitData(id: String): Flow<Resource<OffersVacancies>> {
        return flow {
            val response = searchRemoteDataSource.doRequest(OffersVacanciesRequest(id))

            if (response is OffersVacanciesResponse) {
                emit(
                    Resource.Success(
                        OffersVacancies(
                            response.offerDtoList?.map(offerDtoMapper::map),
                            response.vacancyDtoList?.map(vacancyDtoMapper::map)
                        )
                    )
                )
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