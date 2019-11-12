package com.pgaa.teamvelocity.ui.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pgaa.teamvelocity.R
import com.pgaa.teamvelocity.data.entity.Sprint

class CalculationFragment : Fragment() {

    private lateinit var calculationViewModel: CalculationViewModel
    private var sprintList: List<Sprint>? = null

    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var expectedManDayEditText: EditText

    private lateinit var calculateWhenDontKnowManday: Button
    private lateinit var sprintDaysEditText: EditText
    private lateinit var devCountEditText: EditText
    private lateinit var offManDayEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calculationViewModel =
            ViewModelProviders.of(this).get(CalculationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calculation, container, false)

        expectedManDayEditText = root.findViewById(R.id.editText_expected_manday)
        resultTextView = root.findViewById(R.id.textView_result)
        calculateButton = root.findViewById(R.id.button_calculate)
        calculateButton.setOnClickListener {
            when {
                expectedManDayEditText.text.isNullOrEmpty() -> Toast.makeText(activity, "Enter Expected Man Day", Toast.LENGTH_SHORT).show()
                sprintList.isNullOrEmpty() -> Toast.makeText(activity, "No Sprint is added", Toast.LENGTH_SHORT).show()
                else -> calculationViewModel.calculateExpectedStoryPoint(expectedManDayEditText.text.toString(), sprintList!!)
            }
        }

        sprintDaysEditText = root.findViewById(R.id.editText_days_of_the_sprint)
        devCountEditText = root.findViewById(R.id.editText_dev_count)
        offManDayEditText = root.findViewById(R.id.editText_off_man_day)
        calculateWhenDontKnowManday = root.findViewById(R.id.button_calculate_when_dont_know_manday)
        calculateWhenDontKnowManday.setOnClickListener {
            when {
                sprintDaysEditText.text.isNullOrEmpty() -> Toast.makeText(activity, "Enter Sprint Days", Toast.LENGTH_SHORT).show()
                devCountEditText.text.isNullOrEmpty() -> Toast.makeText(activity, "Enter Dev Count", Toast.LENGTH_SHORT).show()
                offManDayEditText.text.isNullOrEmpty() -> Toast.makeText(activity, "Enter Off Man Days", Toast.LENGTH_SHORT).show()
                sprintList.isNullOrEmpty() -> Toast.makeText(activity, "No Sprint is added", Toast.LENGTH_SHORT).show()
                else -> calculationViewModel.calculateExpectedStoryPoint(
                    sprintDaysEditText.text.toString(),
                    devCountEditText.text.toString(),
                    offManDayEditText.text.toString(),
                    sprintList!!
                )
            }
        }


        calculationViewModel.allSprints.observe(this, Observer {
            sprintList = it
        })
        calculationViewModel.expectedStoryPoint.observe(this, Observer {
            resultTextView.text = it
            resultTextView.visibility = View.VISIBLE
        })

        calculationViewModel.getAllSprint()

        return root
    }
}