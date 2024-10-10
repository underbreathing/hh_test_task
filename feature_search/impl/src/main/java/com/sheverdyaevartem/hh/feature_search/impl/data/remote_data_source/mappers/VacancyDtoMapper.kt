package com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.mappers

import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.VacancyDto
import com.sheverdyaevartem.hh.feature_search.api.domain.model.Vacancy

class VacancyDtoMapper {

    fun map(vacancyDto: VacancyDto): Vacancy {
        return with(vacancyDto) {
            Vacancy(
                address,
                appliedNumber,
                company,
                description,
                experience,
                id,
                isFavorite,
                lookingNumber,
                publishedDate,
                questions,
                responsibilities,
                salary,
                schedules,
                title
            )
        }
    }
}