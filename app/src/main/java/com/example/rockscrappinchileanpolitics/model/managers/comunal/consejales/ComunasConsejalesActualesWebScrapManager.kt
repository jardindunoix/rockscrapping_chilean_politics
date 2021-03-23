package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ComunasConselajesActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity

class ComunasConsejalesActualesWebScrapManager {
	
	var allComunasConsejales = MutableLiveData<MutableList<ComunaConsejalActualEntity>>()
	
	init {
		allComunasConsejales.value = getAllConsejalesActuales()
	}
	
	private fun getAllConsejalesActuales():MutableList<ComunaConsejalActualEntity> {
		val list:MutableList<ComunaConsejalActualEntity>
		val loader = ComunasConselajesActualesWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get()
		return list
	}
}

