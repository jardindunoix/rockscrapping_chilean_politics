package com.example.rockscrappinchileanpolitics.c_model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.repositorio.RepositorioWebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales.ComunaConsejalActualEntity
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
		return RepositorioWebScrapCall.getComunasConsejalesActuales()
	}
}

