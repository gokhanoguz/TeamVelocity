package com.pgaa.teamvelocity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

        sprintViewModel.allSprints.observe(this, Observer {
            //TODO:
        })
        return root
    }
}