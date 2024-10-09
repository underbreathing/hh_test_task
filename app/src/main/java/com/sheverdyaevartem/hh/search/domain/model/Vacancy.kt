package com.sheverdyaevartem.hh.search.domain.model

import com.google.gson.annotations.SerializedName
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.AddressDto
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.ExperienceDto
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.SalaryDto

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