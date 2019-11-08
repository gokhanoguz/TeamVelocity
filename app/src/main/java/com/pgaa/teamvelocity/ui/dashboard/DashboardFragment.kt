package com.pgaa.teamvelocity.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.model.SprintStats
import com.pgaa.teamvelocity.util.SprintUtils.calculateSprintStats

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var avRatioTextView: TextView
    private lateinit var avStoryPointsTextView: TextView
    private lateinit var avManDayTextView: TextView

    var sprinStats: SprintStats? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        avRatioTextView = root.findViewById(R.id.text_av_ratio_value)
        avStoryPointsTextView = root.findViewById(R.id.text_av_storypoint_value)
        avManDayTextView = root.findViewById(R.id.text_av_manday_value)
        dashboardViewModel.allSprints.observe(this, Observer {
            sprinStats = calculateSprintStats(it)
            updateUI()
        })

        dashboardViewModel.getAllSprint()
        return root
    }

    private fun updateUI() {
        sprinStats?.let {
            avRatioTextView.text = "%.2f".format(it.avRatio)
            avStoryPointsTextView.text = "%.2f".format(it.avStoryPoints)
            avManDayTextView.text = "%.2f".format(it.avManDay)
        }
    }
}