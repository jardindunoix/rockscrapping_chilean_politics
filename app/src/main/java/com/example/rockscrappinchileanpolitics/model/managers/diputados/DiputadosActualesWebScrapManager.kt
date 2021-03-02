package com.example.rockscrappinchileanpolitics.model.managers.diputados

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.diputados.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity

class DiputadosActualesWebScrapManager() {
	
	var allDiputadosActuales = MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
		allDiputadosActuales.value = getDiputadosActuales()
	}
	
	fun getDiputadosActuales():MutableList<DiputadoActualEntity> {
		var list = mutableListOf<DiputadoActualEntity>()
		var loader:AsyncTask<Void, Void, ArrayList<DiputadoActualEntity>>? = null
		loader = DiputadosActualesWebScrap.LoadInitNews()
		loader.execute()
		list = loader.get()
		return list
	}
}


