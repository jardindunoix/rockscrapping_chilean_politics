package com.example.rockscrappinchileanpolitics.c_model.d_managers.legislativo.senadores

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.SenadorActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SenadoresActualesWebScrapManager {
	
	var allSenadoresActuales = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allSenadoresActuales.postValue(getSenadoresActuales())
		}
	}
	
	private fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		return WebScrapCall.getSenadoresActuales()
	}
}