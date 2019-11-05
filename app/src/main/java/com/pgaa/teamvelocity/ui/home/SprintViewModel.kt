package com.pgaa.teamvelocity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SprintViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sprint Fragment"
    }
    val text: LiveData<String> = _text
}