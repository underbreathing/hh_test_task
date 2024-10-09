package com.sheverdyaevartem.hh.search.domain.mappers

import com.sheverdyaevartem.hh.search.domain.model.Vacancy
import com.sheverdyaevartem.hh.search.ui.model.VacancyInfo

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