package com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.mappers

import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.OfferDto
import com.sheverdyaevartem.hh.feature_search.api.domain.model.Offer

class OfferDtoMapper {

    fun map(offerDto: OfferDto): Offer {
        return with(offerDto) {
            Offer(
                button?.text,
                id,
                link,
                title
            )
        }
    }
}