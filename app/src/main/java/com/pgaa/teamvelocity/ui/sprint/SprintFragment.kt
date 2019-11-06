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

class SprintFragment : Fragment() {

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
            recyclerView.adapter = SprintAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(it)
        }

        return root
    }
}