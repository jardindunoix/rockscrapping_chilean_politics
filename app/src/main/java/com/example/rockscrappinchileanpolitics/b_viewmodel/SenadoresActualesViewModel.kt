package com.example.rockscrappinchileanpolitics.b_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.b_entities.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.c_model.d_managers.legislativo.senadores.SenadoresActualesManager
import kotlinx.coroutines.launch

class SenadoresActualesViewModel(application:Application):AndroidViewModel(application) {
	
	var senadoresActualesList = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		getSenadoresActualesList()
	}
	
	private fun getSenadoresActualesList() {
		senadoresActualesList = SenadoresActualesManager().allSenadoresActuales
		viewModelScope.launch {
			senadoresActualesList
		}
	}
	
}