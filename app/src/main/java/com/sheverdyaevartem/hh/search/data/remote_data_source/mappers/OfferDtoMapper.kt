package com.sheverdyaevartem.hh.search.data.remote_data_source.mappers

import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.OfferDto
import com.sheverdyaevartem.hh.search.domain.model.Offer

class OfferDtoMapper {

    fun map(offerDto: OfferDto): Offer {
        return with(offerDto) {
            Offer(buttonDto?.text, id, link, title)
        }
    }
}