package com.pgaa.teamvelocity.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint
import com.pgaa.teamvelocity.data.model.SprintStats
import com.pgaa.teamvelocity.util.ReportingUtils
import com.pgaa.teamvelocity.util.SprintUtils.calculateSprintStats

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var avRatioTextView: TextView
    private lateinit var avStoryPointsTextView: TextView
    private lateinit var avManDayTextView: TextView
    private lateinit var createReportButton: Button

    var sprinStats: SprintStats? = null
    var sprintList: List<Sprint>? = null

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
        createReportButton = root.findViewById(R.id.button_create_report)
        dashboardViewModel.allSprints.observe(this, Observer {
            sprintList = it
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

        if (activity != null && sprintList!= null && sprinStats!= null) {
            createReportButton.setOnClickListener { ReportingUtils.createFullReportPdf(activity!!, sprintList!!, sprinStats!!) }
        }
    }
}