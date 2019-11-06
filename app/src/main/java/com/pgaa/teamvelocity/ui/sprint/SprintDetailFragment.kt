package com.pgaa.teamvelocity.ui.sprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint
import kotlinx.android.synthetic.main.fragment_sprint_detail.*

private const val ARG_PARAM_SPRINT = "sprint_param"

class SprintDetailFragment : Fragment() {
    private var sprint: Sprint? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sprint = it.getParcelable(ARG_PARAM_SPRINT)
        }
        //setStyle(STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sprint_detail, container, false)
        var sprintNameEditText = view.findViewById<TextView>(R.id.editText_title)
        sprintNameEditText.text = sprint?.name

        var cancelButton = view.findViewById<Button>(R.id.button_cancel)
        cancelButton.setOnClickListener { activity?.supportFragmentManager?.popBackStack()}

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(sprint: Sprint) =
            SprintDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_SPRINT, sprint)
                }
            }
    }
}
