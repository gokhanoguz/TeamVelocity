package com.pgaa.teamvelocity.ui.sprint

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.ui.sprint.viewmodel.SprintListViewModel

class SprintList : Fragment() {

    companion object {
        fun newInstance() = SprintList()
    }

    private lateinit var viewModel: SprintListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sprint_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SprintListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
