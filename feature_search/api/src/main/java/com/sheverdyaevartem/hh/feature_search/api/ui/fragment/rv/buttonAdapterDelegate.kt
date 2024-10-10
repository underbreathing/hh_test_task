package com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sheverdyaevartem.hh.feature_search.api.databinding.ButtonRvItemBinding
import com.sheverdyaevartem.hh.uikit.RVItem

fun buttonAdapterDelegate(onItemClick: () -> Unit) =
    adapterDelegateViewBinding<ButtonRVItem, RVItem, ButtonRvItemBinding>({ layoutInflater, parent ->
        ButtonRvItemBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {
            itemView.setOnClickListener {
                onItemClick()
            }
            val vacanciesCount = item.countVacancies
            binding.bMoreVacancies.text =
                "Еще $vacanciesCount ${getVacancyDeclension(vacanciesCount)}"
        }
    }

fun getVacancyDeclension(count: Int): String {
    val word = "ваканси"

    return when {
        count % 100 in 11..19 -> "${word}й"
        count % 10 == 1 -> "${word}я"
        count % 10 in 2..4 -> "${word}и"
        else -> "${word}й"
    }
}