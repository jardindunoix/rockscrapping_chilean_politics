package com.example.rockscrappinchileanpolitics.model.managers.legislativo.diputados

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales.ConselajesActualesWebScrap
import com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.diputados.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DiputadosActualesWebScrapManager {
	
	var allDiputadosActuales = MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
		GlobalScope.launch {
			allDiputadosActuales.postValue(getDiputadosActuales())
		}
	}
	
	fun getDiputadosActuales():MutableList<DiputadoActualEntity> {
		val list:MutableList<DiputadoActualEntity>
		list = DiputadosActualesWebScrap.loadInitNews()
		return list
	}
}


