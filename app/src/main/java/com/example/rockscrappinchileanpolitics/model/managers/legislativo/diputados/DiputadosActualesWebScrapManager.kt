package com.example.rockscrappinchileanpolitics.model.managers.legislativo.diputados

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.diputados.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.senadores.SenadoresActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiputadosActualesWebScrapManager {
	
	var allDiputadosActuales = MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allDiputadosActuales.postValue(getDiputadosActuales())
		}
	}
	
	private fun getDiputadosActuales():MutableList<DiputadoActualEntity> {
		return DiputadosActualesWebScrap.doInBackground()
	}
}


