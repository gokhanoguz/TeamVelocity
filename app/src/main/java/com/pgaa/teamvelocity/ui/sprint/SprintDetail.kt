package com.pgaa.teamvelocity.ui.sprint

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.ui.sprint.viewmodel.SprintDetailViewModel

class SprintDetail : Fragment() {

    companion object {
        fun newInstance() = SprintDetail()
    }

    private lateinit var viewModel: SprintDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sprint_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SprintDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
