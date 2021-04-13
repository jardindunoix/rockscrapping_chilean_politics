package com.example.rockscrappinchileanpolitics.c_model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.repositorio.RepositorioWebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales.ConsejalActualDetalleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsejalesActualesDetalleWebScrapManager(val comuna:String) {
	
	var allConsejales = MutableLiveData<MutableList<ConsejalActualDetalleEntity>>()
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allConsejales.postValue(getAllConsejalesActuales(comuna))
		}
	}
	
	private fun getAllConsejalesActuales(comuna:String):MutableList<ConsejalActualDetalleEntity> {
		return RepositorioWebScrapCall.getConsejalesActualesDetail(comuna)
	}
}

