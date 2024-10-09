package com.sheverdyaevartem.hh.search.data.remote_data_source.mappers

import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.VacancyDto
import com.sheverdyaevartem.hh.search.domain.model.Vacancy

class VacancyDtoMapper {

    fun map(vacancyDto: VacancyDto): Vacancy {
        return with(vacancyDto) {
            Vacancy(
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