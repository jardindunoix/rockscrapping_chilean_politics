package com.example.rockscrappinchileanpolitics.model.managers.legislativo.senadores

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.senadores.SenadoresActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity

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