package com.sheverdyaevartem.hh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sheverdyaevartem.hh.databinding.ActivityRootBinding
import com.sheverdyaevartem.hh.uikit.FavoriteVacanciesIndicator
import com.sheverdyaevartem.hh.uikit.R as RUI

class RootActivity : AppCompatActivity(), FavoriteVacanciesIndicator {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        val navController = navHostFragment.navController

//        val navGraph = navController.navInflater.inflate(R.navigation.main_navigation_graph)
//
//        navGraph.setStartDestination(R.id.fragmentSearch)
//
//        //достем id из sharedPreferences
//        val id = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"
//
//        navController.setGraph(navGraph,FragmentSearch.createArgs(id))

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.fragmentSign, R.id.fragmentCodeEntry -> {
                    binding.bottomNavigationPanel.isVisible = false
                }

                else -> {
                    binding.bottomNavigationPanel.isVisible = true
                }
            }
        }

        binding.bottomNavigationPanel.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
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
}