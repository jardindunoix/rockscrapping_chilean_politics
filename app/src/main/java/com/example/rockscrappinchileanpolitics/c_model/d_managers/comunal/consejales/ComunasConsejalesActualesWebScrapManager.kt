package com.example.rockscrappinchileanpolitics.c_model.d_managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ComunaConsejalActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComunasConsejalesActualesWebScrapManager {
	
	var allComunasConsejales = MutableLiveData<MutableList<ComunaConsejalActualEntity>>()
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allComunasConsejales.postValue(getAllConsejalesActuales())
		}
	}
	
	private fun getAllConsejalesActuales():MutableList<ComunaConsejalActualEntity> {
		return WebScrapCall.getComunasConsejalesActuales()
	}
}

