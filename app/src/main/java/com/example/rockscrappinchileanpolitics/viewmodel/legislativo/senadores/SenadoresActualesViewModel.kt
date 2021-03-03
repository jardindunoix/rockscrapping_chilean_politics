package com.example.rockscrappinchileanpolitics.viewmodel.legislativo.senadores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.legislativo.senadores.SenadoresActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import kotlinx.coroutines.launch

class SenadoresActualesViewModel(application:Application):AndroidViewModel(application) {
	var senadoresActualesList = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		if (senadoresActualesList.value.isNullOrEmpty()) {
			getSenadoresActualesList()
		}
	}
	
	private fun getSenadoresActualesList() {
		senadoresActualesList = SenadoresActualesWebScrapManager().allSenadoresActuales
		viewModelScope.launch {
			senadoresActualesList
		}
	}
	
}