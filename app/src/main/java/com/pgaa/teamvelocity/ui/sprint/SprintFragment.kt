package com.pgaa.teamvelocity.ui.sprint

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
        val fab = root.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { startSprintDetailFragment(null) }

        val recyclerView = root.findViewById<RecyclerView>(R.id.sprint_recyclerview)

        activity?.let { activity ->
            val adapter = SprintAdapter(activity, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
            sprintViewModel.allSprints.observe(this, Observer { sprintList ->
                adapter.setSprintList(sprintList)

            })
        }
        sprintViewModel.getAllSprint()
        return root
    }

    override fun onSprintClick(sprint: Sprint) {
        startSprintDetailFragment(sprint)
    }

    override fun onDeleteClick(sprint: Sprint) {

        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete Sprint")
                .setMessage("This is irreversible! Are you sure you want to delete ${sprint.name}")
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("Yes") { dialog, _ ->
                    sprintViewModel.deleteSprint(sprint)
                    sprintViewModel.getAllSprint()
                    dialog.dismiss() }
                .create().show()

        }
    }

    private fun startSprintDetailFragment(sprint: Sprint?) {
        val detailFragment = SprintDetailFragment.newInstance(sprint)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, detailFragment)
            ?.addToBackStack("detail_fragment")
            ?.commit()
    }
}