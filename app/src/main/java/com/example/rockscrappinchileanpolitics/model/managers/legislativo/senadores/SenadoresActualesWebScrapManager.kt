package com.example.rockscrappinchileanpolitics.model.managers.legislativo.senadores

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.senadores.SenadoresActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SenadoresActualesWebScrapManager() {
	
	var allSenadoresActuales = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allSenadoresActuales.postValue(getSenadoresActuales())
		}
	}
	
	private fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		return SenadoresActualesWebScrap.doInBackground()
	}
}