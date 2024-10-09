package com.sheverdyaevartem.hh.search.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sheverdyaevartem.hh.databinding.FragmentSearchBinding
import com.sheverdyaevartem.hh.search.ui.fragment.rv.FictiveRVItem
import com.sheverdyaevartem.hh.search.ui.fragment.rv.OfferRVItem
import com.sheverdyaevartem.hh.search.ui.fragment.rv.offerAdapterDelegate
import com.sheverdyaevartem.hh.search.ui.fragment.rv.offerFictiveAdapterDelegate
import com.sheverdyaevartem.hh.search.ui.model.OfferInfo
import com.sheverdyaevartem.hh.search.ui.view_model.SearchViewModel
import com.sheverdyaevartem.hh.search.ui.view_model.states.InitDataState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class FragmentSearch : Fragment() {

    companion object {
        private const val ID_KEY = "id_key"

        fun createArgs(id: String): Bundle {
            return bundleOf(ID_KEY to id)
        }
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var viewModel: SearchViewModel? = null

    private val adapter = ListDelegationAdapter(
        offerAdapterDelegate { item ->
            openLink(item)
        },
        offerFictiveAdapterDelegate()

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

        arguments?.let {
            viewModel = getViewModel { parametersOf(it.getString(ID_KEY)) }
        }


        viewModel?.initData?.observe(viewLifecycleOwner) { initDataState ->
            when (initDataState) {
                is InitDataState.Content -> showContent(initDataState.data)
                InitDataState.ConnectionError -> showConnectionError()
                InitDataState.Empty -> showEmpty()
                InitDataState.InternalError -> showInternalError()
                InitDataState.IsLoading -> showLoading()
                InitDataState.ServerError -> showServerError()
            }
        }

        binding.rvOffers.adapter = adapter
    }

    private fun openLink(item: OfferRVItem) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri.parse(item.offerInfo.link)
            )
        )
    }

    private fun showServerError() {
        showEmpty()
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
        val layoutParams = snackbar.view.layoutParams as FrameLayout.LayoutParams
        layoutParams.gravity = Gravity.CENTER
        snackbar.view.layoutParams = layoutParams
        snackbar.show()
    }

    private fun showLoading() {
        adapter.items = List(3) { FictiveRVItem() }
        adapter.notifyDataSetChanged()
    }

    private fun showInternalError() {
        showEmpty()
    }

    private fun showEmpty() {
        binding.rvOffers.isVisible = false
    }

    private fun showContent(data: List<OfferRVItem>) {
        binding.rvOffers.isVisible = true
        adapter.items = data
        adapter.notifyDataSetChanged()
    }

    private fun showConnectionError() {
        showSnackBar("Проверьте интернет соединение")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}