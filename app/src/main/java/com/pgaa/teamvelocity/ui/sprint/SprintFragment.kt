package com.pgaa.teamvelocity.ui.sprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint

class SprintFragment : Fragment(), OnSprintInteractionListener {

    private lateinit var sprintViewModel: SprintViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sprintViewModel =
            ViewModelProviders.of(this).get(SprintViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sprint, container, false)

        sprintViewModel.allSprints?.observe(this, Observer {
            //TODO:
        })

        val recyclerView = root.findViewById<RecyclerView>(R.id.sprint_recyclerview)
        activity?.let {
            val adapter = SprintAdapter(it, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(it)
            addSampleData(adapter)
        }

        return root
    }

    override fun onSprintInteraction(sprint: Sprint) {
        val detailFragment = SprintDetailFragment.newInstance(sprint)
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack("detail_fragment")
                .commit()
        }
    }

    private fun addSampleData(adapter: SprintAdapter) {
        var dummySprint = Sprint("dummy sprint", "4", "", 50, 1.75)
        var dummySprint2 = Sprint("dummy sprint2", "4", "", 50, 1.75)
        adapter.setSprintList(listOf(dummySprint, dummySprint2))
    }
}