package com.pgaa.teamvelocity.ui.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pgaa.teamvelocity.R

class CalculationFragment : Fragment() {

    private lateinit var notificationsViewModel: CalculationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(CalculationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calculation, container, false)
        val textView: TextView = root.findViewById(R.id.text_calculation)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}