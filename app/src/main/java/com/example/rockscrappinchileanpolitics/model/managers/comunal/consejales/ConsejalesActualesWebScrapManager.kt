package com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ConselajesActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConsejalesActualesWebScrapManager {
	
	var allConsejales = MutableLiveData<MutableList<ConsejalActualEntity>>()
	
	init {
		GlobalScope.launch(Dispatchers.IO) {
			allConsejales.postValue(getAllConsejalesActuales())
		}
		
	}
	
	private fun getAllConsejalesActuales():MutableList<ConsejalActualEntity> {
		val list:MutableList<ConsejalActualEntity>
		list = ConselajesActualesWebScrap.loadInitNews()
		Log.d("LISTA ---->", list.toString())
		return list
	}
}