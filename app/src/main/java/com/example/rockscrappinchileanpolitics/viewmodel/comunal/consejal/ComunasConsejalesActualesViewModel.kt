package com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales.ComunasConsejalesActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import kotlinx.coroutines.launch

class ComunasConsejalesActualesViewModel(application:Application):AndroidViewModel(application) {
	
	var comunasConsejalesActualesList = MutableLiveData<MutableList<ComunaConsejalActualEntity>>(mutableListOf())
	
	init {
		if (comunasConsejalesActualesList.value.isNullOrEmpty()) {
			getConsejalesActualesList()
			
		}
	}
	
	private fun getConsejalesActualesList() {
		comunasConsejalesActualesList = ComunasConsejalesActualesWebScrapManager().allConsejales
		viewModelScope.launch {
			comunasConsejalesActualesList
		}
	}
	
}
