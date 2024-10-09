package com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sheverdyaevartem.hh.feature_search.api.R
import com.sheverdyaevartem.hh.feature_search.api.databinding.OfferRvFictiveItemBinding
import com.sheverdyaevartem.hh.feature_search.api.databinding.OfferRvItemBinding
import com.sheverdyaevartem.hh.uikit.RVItem


fun offerFictiveAdapterDelegate() =
    adapterDelegateViewBinding<FictiveRVItem, RVItem, OfferRvFictiveItemBinding>({ layoutInflater, parent ->
        OfferRvFictiveItemBinding.inflate(layoutInflater, parent, false)
    }) {
        val shimmerAnimation =
            AnimationUtils.loadAnimation(itemView.context, R.anim.shimmer_anim)
        binding.offerItemLayout.startAnimation(shimmerAnimation)
    }

fun offerAdapterDelegate(onItemClicked: (OfferRVItem) -> Unit) =
    adapterDelegateViewBinding<OfferRVItem, RVItem, OfferRvItemBinding>({ layoutInflater: LayoutInflater, parent: ViewGroup ->
        OfferRvItemBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {
            item.buttonText?.let {
                binding.tvButton.isVisible = true
                binding.tvButton.text = it
            }
            binding.tvTitle.text = item.title.trim()
            item.id?.let {
                val drawableRes = getDrawableResource(it)
                if (drawableRes != -1) {
                    binding.ivIcon.isVisible = true
                    binding.ivIcon.setImageResource(drawableRes)
                }
            }
            itemView.setOnClickListener {
                onItemClicked(item)
            }
        }
    }


private fun getDrawableResource(id: String): Int {
    return when (id) {
        "near_vacancies" -> R.drawable.near_vacancies
        "level_up_resume" -> R.drawable.level_up_resume
        "temporary_job" -> R.drawable.temporary_job
        else -> -1
    }
}