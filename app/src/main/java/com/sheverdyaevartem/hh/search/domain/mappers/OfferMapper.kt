package com.sheverdyaevartem.hh.search.domain.mappers

import com.sheverdyaevartem.hh.search.domain.model.Offer
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo

class OfferMapper {
    fun map(offer: Offer): OfferInfo {
        return with(offer) { OfferInfo(buttonText, id, link, title) }
    }
}