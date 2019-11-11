package com.pgaa.teamvelocity.ui.calculation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgaa.teamvelocity.data.entity.Sprint
import com.pgaa.teamvelocity.data.local.SprintDatabase
import com.pgaa.teamvelocity.data.repository.SprintRepository
import com.pgaa.teamvelocity.util.SprintUtils
import kotlinx.coroutines.launch

class CalculationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SprintRepository
    var allSprints: MutableLiveData<List<Sprint>>
    var expectedStoryPoint: MutableLiveData<String>

    init {
        val sprinData = SprintDatabase.getDatabase(application).sprintDao()
        repository = SprintRepository(sprinData)
        allSprints = MutableLiveData()
        expectedStoryPoint = MutableLiveData()
    }

    fun getAllSprint() {
        viewModelScope.launch {
            allSprints.value = repository.getAllSprints()
        }
    }

    fun calculateExpectedStoryPoint(expectedManDay: String, sprintList: List<Sprint>) {
        viewModelScope.launch {
            var ratio = SprintUtils.calculateSprintStats(sprintList).avRatio

            expectedStoryPoint.value = "%.2f".format(ratio.toDouble() * expectedManDay.toDouble())
        }
    }
}