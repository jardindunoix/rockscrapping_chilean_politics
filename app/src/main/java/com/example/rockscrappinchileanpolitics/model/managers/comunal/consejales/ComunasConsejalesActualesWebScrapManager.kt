package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ComunasConselajesActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
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
		return ComunasConselajesActualesWebScrap.doInBackground()
	}
}

