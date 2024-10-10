package com.sheverdyaevartem.hh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sheverdyaevartem.hh.databinding.ActivityRootBinding
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.FragmentCodeEntry
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.navigation.LoginNavigator
import com.sheverdyaevartem.hh.uikit.FavoriteVacanciesIndicator
import com.sheverdyaevartem.hh.uikit.R as RUI

class RootActivity : AppCompatActivity(), FavoriteVacanciesIndicator, LoginNavigator {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!

    private var mainNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        mainNavController = navHostFragment.navController

        mainNavController?.let {
            binding.bottomNavigationPanel.setupWithNavController(it)
        }

        mainNavController?.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.fragmentLogin, R.id.fragmentCodeEntry -> {
                    binding.bottomNavigationPanel.isVisible = false
                }

                else -> {
                    binding.bottomNavigationPanel.isVisible = true
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainNavController = null
        _binding = null
    }

    override fun updateIndicator(newCount: Int) {
        if (newCount >= 0) {
            val badge = binding.bottomNavigationPanel.getOrCreateBadge(R.id.fragmentFavourites)
            badge.backgroundColor = ContextCompat.getColor(this, RUI.color.red)
            badge.badgeTextColor = ContextCompat.getColor(this, RUI.color.white)
            badge.number = newCount

        }
    }

    override fun navigateToCodeEntry(email: String) {
        mainNavController?.navigate(
            R.id.action_fragmentLogin_to_fragmentCodeEntry,
            FragmentCodeEntry.createArgs(email)
        )
    }

    override fun navigateToFragmentSearch() {
        mainNavController?.setGraph(R.navigation.main_navigation_graph)
    }
}