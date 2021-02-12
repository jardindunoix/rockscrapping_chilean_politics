package com.example.rockscrappinchileanpolitics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.DiputadosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import kotlinx.coroutines.launch

class PoliticsViewModel(application : Application) : AndroidViewModel(application) {
	
	var diputadosActualesList : MutableLiveData<MutableList<DiputadoActualEntity>> =
		MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
		if (diputadosActualesList.value.isNullOrEmpty()) {
			getDiputadosActualesList()
		}
	}
	
	private fun getDiputadosActualesList() {
		diputadosActualesList =
			DiputadosActualesWebScrapManager(getApplication()).allDiputadosActuales
		viewModelScope.launch {
//			diputadosActualesList
		}
	}
}