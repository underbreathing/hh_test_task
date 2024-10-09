package com.sheverdyaevartem.hh.search.ui.fragment.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sheverdyaevartem.hh.R
import com.sheverdyaevartem.hh.core.ui.RVItem
import com.sheverdyaevartem.hh.databinding.OfferRvFictiveItemBinding
import com.sheverdyaevartem.hh.databinding.OfferRvItemBinding


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
            item.offerInfo.buttonText?.let {
                binding.tvButton.isVisible = true
                binding.tvButton.text = it
            }
            binding.tvTitle.text = item.offerInfo.title.trim()
            item.offerInfo.id?.let {
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