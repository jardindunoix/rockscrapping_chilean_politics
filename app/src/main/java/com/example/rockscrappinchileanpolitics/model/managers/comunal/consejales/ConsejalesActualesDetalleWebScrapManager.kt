package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ComunasConselajesActualesWebScrap
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ConsejalesActualesDetalleWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity

class ConsejalesActualesDetalleWebScrapManager {
	
	var allConsejales = MutableLiveData<MutableList<ConsejalActualDetalleEntity>>()
	
	init {
		allConsejales.value = getAllConsejalesActuales()
	}
	
	private fun getAllConsejalesActuales():MutableList<ConsejalActualDetalleEntity> {
		val list:MutableList<ConsejalActualDetalleEntity>
		val loader = ConsejalesActualesDetalleWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get()
		return list
	}
}

