package com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv

import com.sheverdyaevartem.hh.uikit.RVItem

data class OfferRVItem(
    val buttonText: String?,
    val id: String?,
    val link: String?,
    val title: String
) : RVItem