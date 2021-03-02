package com.example.rockscrappinchileanpolitics.model.managers.senadores

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.senadores.SenadoresActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.senadores.SenadorActualEntity

class SenadoresActualesWebScrapManager() {
	
	var allSenadoresActuales = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		allSenadoresActuales.value = getSenadoresActuales()
	}
	
	fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		var list = mutableListOf<SenadorActualEntity>()
		var loader:AsyncTask<Void, Void, ArrayList<SenadorActualEntity>>? = null
		loader = SenadoresActualesWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get()
		return list
	}
}