package com.example.rockscrappinchileanpolitics.model.managers.legislativo.senadores

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.senadores.SenadoresActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SenadoresActualesWebScrapManager() {
	
	var allSenadoresActuales = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		GlobalScope.launch {
			allSenadoresActuales.postValue(getSenadoresActuales())
		}
	}
	
	fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		val list:MutableList<SenadorActualEntity>
		list = SenadoresActualesWebScrap.loadInitNews()
		return list
	}
}