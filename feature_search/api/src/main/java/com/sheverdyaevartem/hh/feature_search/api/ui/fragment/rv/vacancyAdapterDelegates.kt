package com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv

import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sheverdyaevartem.hh.feature_search.api.R
import com.sheverdyaevartem.hh.feature_search.api.databinding.VacancyRvFictiveItemBinding
import com.sheverdyaevartem.hh.feature_search.api.databinding.VacancyRvItemBinding
import com.sheverdyaevartem.hh.uikit.RVItem
import java.time.LocalDate


fun vacancyFictiveAdapterDelegate() =
    adapterDelegateViewBinding<VacancyFictiveRVItem, RVItem, VacancyRvFictiveItemBinding>({ inflater, parent ->
        VacancyRvFictiveItemBinding.inflate(inflater, parent, false)
    }) {
        val shimmerAnimation =
            AnimationUtils.loadAnimation(itemView.context, R.anim.shimmer_anim)
        binding.frameVacancyFictive.startAnimation(shimmerAnimation)
    }

fun vacancyAdapterDelegate() =
    adapterDelegateViewBinding<VacancyRVItem, RVItem, VacancyRvItemBinding>({ layoutInflater, parent ->
        VacancyRvItemBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {

            val nowLooking = item.lookingNumber
            val town = item.addressDto?.town
            val company = item.company
            val experience = item.experienceDto?.previewText
            binding.tvVacancyTitle.text = item.title
            val salary = item.salaryDto?.short
            val date = item.publishedDate
            if (item.isFavorite) {
                binding.bLike.setImageResource(R.drawable.favorite_pressed)
            }
            if (date != null) {
                binding.tvPublicationDate.text = "Опубликовано ${formatDate(date)}"
            } else {
                binding.tvPublicationDate.isVisible = false
            }
            if (salary != null) {
                binding.tvSalary.text = salary
            } else {
                binding.tvSalary.isVisible = false
            }
            if (nowLooking == null) {
                binding.tvNowSeeing.visibility = View.INVISIBLE
            } else {
                binding.tvNowSeeing.text = "Сейчас просматривает ${getManDeclension(nowLooking)}"
                binding.tvNowSeeing.visibility = View.VISIBLE
                //TODO доделать склонение
            }
            if (town != null) {
                binding.tvCity.text = town
            } else {
                binding.tvCity.isVisible = false
            }
            if (company != null) {
                binding.tvCompany.text = company
            } else {
                binding.tvCompany.isVisible = false
            }
            if (experience != null) {
                binding.tvExperience.text = experience
            } else {
                binding.tvExperience.isVisible = false
            }
        }
    }

fun getManDeclension(number: Int): String {
    val wordForm = when {
        number % 10 == 1 && number % 100 != 11 -> "человек"
        number % 10 in 2..4 && (number % 100 < 12 || number % 100 > 14) -> "человека"
        else -> "человек"
    }
    return "$number $wordForm"
}


fun formatDate(dateString: String): String {

    val date = LocalDate.parse(dateString)

    val day = date.dayOfMonth
    val month = getMonthName(date.monthValue)

    return "$day $month"
}

fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "января"
        2 -> "февраля"
        3 -> "марта"
        4 -> "апреля"
        5 -> "мая"
        6 -> "июня"
        7 -> "июля"
        8 -> "августа"
        9 -> "сентября"
        10 -> "октября"
        11 -> "ноября"
        12 -> "декабря"
        else -> ""
    }
}