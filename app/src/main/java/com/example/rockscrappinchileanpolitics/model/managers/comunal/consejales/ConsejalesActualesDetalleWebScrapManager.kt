package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ConsejalesActualesDetailWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity

class ConsejalesActualesDetalleWebScrapManager {
	
	var allConsejales = MutableLiveData<MutableList<ConsejalActualDetalleEntity>>()
	
	init {
		allConsejales.value = getAllConsejalesActuales()
	}
	
	private fun getAllConsejalesActuales():MutableList<ConsejalActualDetalleEntity> {
		val list:MutableList<ConsejalActualDetalleEntity>
		val loader = ConsejalesActualesDetailWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get() !!
		return list
	}
}

