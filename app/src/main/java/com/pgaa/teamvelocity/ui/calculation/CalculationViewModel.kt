package com.pgaa.teamvelocity.ui.calculation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is calculation Fragment"
    }
    val text: LiveData<String> = _text
}