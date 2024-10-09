package com.sheverdyaevartem.hh.feature_search.api.domain.model

import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.AddressDto
import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.ExperienceDto
import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.SalaryDto

data class Vacancy(
    val addressDto: AddressDto?,
    val appliedNumber: Int?,
    val company: String?,
    val description: String?,
    val experienceDto: ExperienceDto?,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int?,
    val publishedDate: String?,
    val questions: List<String>?,
    val responsibilities: String?,
    val salaryDto: SalaryDto?,
    val schedules: List<String>?,
    val title: String
)