package com.example.rockscrappinchileanpolitics.viewmodel.legislativo.diputados

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.legislativo.diputados.DiputadosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import kotlinx.coroutines.launch

class DiputadosActualesViewModel(application:Application):AndroidViewModel(application) {
	
	var diputadosActualesList = MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
		if (diputadosActualesList.value.isNullOrEmpty()) {
			getDiputadosActualesList()
			
		}
	}
	
	private fun getDiputadosActualesList() {
		diputadosActualesList = DiputadosActualesWebScrapManager().allDiputadosActuales
		viewModelScope.launch {
			diputadosActualesList
		}
	}
	
}
