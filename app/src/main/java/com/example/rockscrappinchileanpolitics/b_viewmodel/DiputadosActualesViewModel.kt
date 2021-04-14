package com.example.rockscrappinchileanpolitics.b_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.b_entities.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.c_model.d_managers.legislativo.diputados.DiputadosActualesManager
import kotlinx.coroutines.launch

class DiputadosActualesViewModel(application:Application):AndroidViewModel(application) {
	
	var diputadosActualesList = MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
			getDiputadosActualesList()
	}
	
	private fun getDiputadosActualesList() {
		diputadosActualesList = DiputadosActualesManager().allDiputadosActuales
		viewModelScope.launch {
			diputadosActualesList
		}
	}
	
}
