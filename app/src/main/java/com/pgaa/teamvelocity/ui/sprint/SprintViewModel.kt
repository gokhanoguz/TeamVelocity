package com.pgaa.teamvelocity.ui.sprint

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pgaa.teamvelocity.data.entity.Sprint
import com.pgaa.teamvelocity.data.local.SprintDatabase
import com.pgaa.teamvelocity.data.repository.SprintRepository
import kotlinx.coroutines.launch

class SprintViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SprintRepository
    var allSprints: MutableLiveData<List<Sprint>>

    init {
        val sprinData = SprintDatabase.getDatabase(application, viewModelScope).sprintDao()
        repository = SprintRepository(sprinData)
        allSprints = MutableLiveData()
    }

    fun getAllSprint() {
        viewModelScope.launch {
            allSprints.value = repository.getAllSprints()
        }
    }

    fun addSprint(sprint: Sprint) = viewModelScope.launch {
        repository.insertSprint(sprint)
    }

    fun deleteSprint(sprint: Sprint) = viewModelScope.launch {
        repository.deleteSprint(sprint)
    }
}