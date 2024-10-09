package com.sheverdyaevartem.hh.feature_search.api.ui.mappers

import com.sheverdyaevartem.hh.feature_search.api.domain.model.Offer
import com.sheverdyaevartem.hh.feature_search.api.ui.model.OfferInfo

class OfferMapper {
    fun map(offer: Offer): OfferInfo {
        return with(offer) { OfferInfo(buttonText, id, link, title) }
    }
}