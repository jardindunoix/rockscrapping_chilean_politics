package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ConsejalesActualesDetailWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
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
		return ConsejalesActualesDetailWebScrap.doInBackground(comuna)
	}
}

