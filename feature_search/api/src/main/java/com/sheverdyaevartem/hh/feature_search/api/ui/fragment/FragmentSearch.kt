package com.sheverdyaevartem.hh.feature_search.api.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sheverdyaevartem.hh.feature_search.api.databinding.FragmentSearchBinding
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.FictiveRVItem
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.OfferRVItem
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.OfferVacancyRVItems
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.VacancyFictiveRVItem
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.offerAdapterDelegate
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.offerFictiveAdapterDelegate
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.vacancyAdapterDelegate
import com.sheverdyaevartem.hh.feature_search.api.ui.fragment.rv.vacancyFictiveAdapterDelegate
import com.sheverdyaevartem.hh.feature_search.api.ui.view_model.SearchViewModel
import com.sheverdyaevartem.hh.feature_search.api.ui.view_model.states.InitDataState
import com.sheverdyaevartem.hh.uikit.FavoriteVacanciesIndicator
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class FragmentSearch : Fragment() {

    companion object {
        private const val NETWORK_ID_KEY = "id_key"

        fun createArgs(id: String): Bundle {
            return bundleOf(NETWORK_ID_KEY to id)
        }
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var viewModel: SearchViewModel? = null

    private val offerAdapter = ListDelegationAdapter(
        offerAdapterDelegate { item ->
            openLink(item)
        },
        offerFictiveAdapterDelegate()
    )

    private val vacancyAdapter = ListDelegationAdapter(
        vacancyAdapterDelegate(),
        vacancyFictiveAdapterDelegate()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireNotNull(arguments?.getString(NETWORK_ID_KEY))

        viewModel = getViewModel { parametersOf(id) }


        viewModel?.initData?.observe(viewLifecycleOwner) { initDataState ->
            when (initDataState) {
                is InitDataState.Content -> {
                    showContent(initDataState.data)
                    displayFavoriteCount(initDataState.countOfFavorites)
                }

                InitDataState.ConnectionError -> showConnectionError()
                InitDataState.InternalError -> showInternalError()
                InitDataState.IsLoading -> showLoading()
                InitDataState.ServerError -> showServerError()
            }
        }

        binding.rvOffers.adapter = offerAdapter
        binding.rvVacancies.adapter = vacancyAdapter
    }

    private fun displayFavoriteCount(countOfFavorites: Int) {
        activity?.let {
            (it as FavoriteVacanciesIndicator).updateIndicator(countOfFavorites)
        }
    }

    private fun openLink(item: OfferRVItem) {
        item.link?.let { link ->
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(link)
                )
            )
        }
    }

    private fun showServerError() {
        showOffersEmpty()
    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
        val layoutParams = snackBar.view.layoutParams as FrameLayout.LayoutParams
        layoutParams.gravity = Gravity.CENTER
        snackBar.view.layoutParams = layoutParams
        snackBar.show()
    }

    private fun showLoading() {
        offerAdapter.items = List(3) { FictiveRVItem() }
        offerAdapter.notifyDataSetChanged()
        vacancyAdapter.items = List(2) { VacancyFictiveRVItem() }
        vacancyAdapter.notifyDataSetChanged()
    }

    private fun showInternalError() {
        showOffersEmpty()
        showOffersEmpty()
    }

    private fun showOffersEmpty() {
        binding.rvOffers.isVisible = false
    }

    private fun showVacanciesEmpty() {
        binding.rvVacancies.isVisible = false
    }

    private fun showContent(data: OfferVacancyRVItems) {
        if (data.offerRVItems.isEmpty()) {
            showOffersEmpty()
        } else {
            binding.rvOffers.isVisible = true
            offerAdapter.items = data.offerRVItems
            offerAdapter.notifyDataSetChanged()
        }
        if (data.vacancyRVItems.isEmpty()) {
            showVacanciesEmpty()
        } else {
            //сделать в макете невидимым
            binding.rvVacancies.isVisible = true
            vacancyAdapter.items = data.vacancyRVItems
            vacancyAdapter.notifyDataSetChanged()
        }
    }

    private fun showConnectionError() {
        showSnackBar("Проверьте интернет соединение")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel = null
    }
}