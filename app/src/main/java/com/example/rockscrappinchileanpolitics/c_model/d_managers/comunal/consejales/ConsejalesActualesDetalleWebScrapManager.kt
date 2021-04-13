package com.example.rockscrappinchileanpolitics.c_model.d_managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ConsejalActualDetalleEntity
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
		return WebScrapCall.getConsejalesActualesDetail(comuna)
	}
}

