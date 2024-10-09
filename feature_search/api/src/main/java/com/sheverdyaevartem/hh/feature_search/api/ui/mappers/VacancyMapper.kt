package com.sheverdyaevartem.hh.feature_search.api.ui.mappers

import com.sheverdyaevartem.hh.feature_search.api.domain.model.Vacancy
import com.sheverdyaevartem.hh.feature_search.api.ui.model.VacancyInfo

class VacancyMapper {
    fun map(vacancy: Vacancy): VacancyInfo {
        return with(vacancy) {
            VacancyInfo(
                addressDto,
                appliedNumber,
                company,
                description,
                experienceDto,
                id,
                isFavorite,
                lookingNumber,
                publishedDate,
                questions,
                responsibilities,
                salaryDto,
                schedules,
                title
            )
        }
    }
}