package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ConselajesActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity

class ConsejalesActualesWebScrapManager {
	
	var allConsejales = MutableLiveData<MutableList<String>>()
	
	init {
		allConsejales.value = getAllConsejalesActuales()
	}
	
	private fun getAllConsejalesActuales():MutableList<String> {
		val list:MutableList<String>
		val loader = ConselajesActualesWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get().sorted().toMutableList()
		return list
	}
}

