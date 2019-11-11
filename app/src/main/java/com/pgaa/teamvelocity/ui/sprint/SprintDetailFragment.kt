package com.pgaa.teamvelocity.ui.sprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint
import kotlinx.android.synthetic.main.fragment_sprint_detail.*

private const val ARG_PARAM_SPRINT = "sprint_param"

class SprintDetailFragment : Fragment() {

    private lateinit var sprintViewModel: SprintViewModel

    private var sprint: Sprint? = null
    private lateinit var sprintNameEditText: EditText
    private lateinit var quarterditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var storyPointEditText: EditText
    private lateinit var manDayEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sprintViewModel =
            ViewModelProviders.of(this).get(SprintViewModel::class.java)
        arguments?.let {
            sprint = it.getParcelable(ARG_PARAM_SPRINT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sprint_detail, container, false)
        sprintNameEditText = view.findViewById(R.id.editText_title)
        quarterditText = view.findViewById(R.id.editText_querter)
        descriptionEditText = view.findViewById(R.id.editText_description)
        storyPointEditText = view.findViewById(R.id.editText_storypoints)
        manDayEditText = view.findViewById(R.id.editText_manday)

        sprintNameEditText.setText(sprint?.name)
        quarterditText.setText(sprint?.quarter)
        descriptionEditText.setText(sprint?.description)
        storyPointEditText.setText(sprint?.storyPoints?.toString())
        manDayEditText.setText(sprint?.mandDay?.toString())

        var cancelButton = view.findViewById<Button>(R.id.button_cancel)
        cancelButton.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }

        var saveButton = view.findViewById<Button>(R.id.button_save)
        saveButton.setOnClickListener {
            saveSprint()
        }
        return view
    }

    private fun saveSprint() {
        if (validateFileds()) {
            var sprint = Sprint(
                sprintNameEditText.text.toString(),
                quarterditText.text.toString(),
                descriptionEditText.text.toString(),
                storyPointEditText.text.toString().toDouble(),
                manDayEditText.text.toString().toDouble(),
                storyPointEditText.text.toString().toDouble() / manDayEditText.text.toString().toDouble()
            )
            sprintViewModel.addSprint(sprint)
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun validateFileds(): Boolean {
        if (sprintNameEditText.text.isNullOrEmpty() ||
            storyPointEditText.text.isNullOrEmpty() ||
            quarterditText.text.isNullOrEmpty() ||
            manDayEditText.text.isNullOrEmpty()
        ) {
            Toast.makeText(activity, "Fill mandatory fields", Toast.LENGTH_SHORT).show()
            return false
        } else return true

    }

    companion object {
        @JvmStatic
        fun newInstance(sprint: Sprint?) =
            SprintDetailFragment().apply {
                sprint?.let {
                    arguments = Bundle().apply {
                        putParcelable(ARG_PARAM_SPRINT, sprint)
                    }
                }
            }
    }
}
