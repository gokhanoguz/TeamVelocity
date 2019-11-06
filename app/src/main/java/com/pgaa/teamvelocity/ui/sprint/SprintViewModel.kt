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
    var allSprints: MutableLiveData<List<Sprint>>? = null

    init {
        val sprinData = SprintDatabase.getDatabase(application).sprintDao()
        repository = SprintRepository(sprinData)
        viewModelScope.launch {
            allSprints = MutableLiveData(repository.getAllSprints())
        }
    }

    fun addSprint(sprint: Sprint) = viewModelScope.launch {
        repository.insertSprint(sprint)
    }
}