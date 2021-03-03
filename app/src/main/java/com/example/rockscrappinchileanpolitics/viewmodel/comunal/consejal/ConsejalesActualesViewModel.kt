package com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales.ConsejalesActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import kotlinx.coroutines.launch

class ConsejalesActualesViewModel(application:Application):AndroidViewModel(application) {
	
	var consejalesActualesList = MutableLiveData<MutableList<ConsejalActualEntity>>(mutableListOf())
	
	init {
		if (consejalesActualesList.value.isNullOrEmpty()) {
			getConsejalesActualesList()
			
		}
	}
	
	private fun getConsejalesActualesList() {
		consejalesActualesList = ConsejalesActualesWebScrapManager().allConsejales
		viewModelScope.launch {
			consejalesActualesList
		}
	}
	
}
