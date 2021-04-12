package com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.senadores

import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss.Companion.getSenadoresActuales
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import java.io.IOException

class SenadoresActualesWebScrap {
	
	companion object {
		
		private var senadoresActualesList:MutableList<SenadorActualEntity> = mutableListOf()
		
		fun doInBackground():MutableList<SenadorActualEntity> {
			try {
				senadoresActualesList = getSenadoresActuales()
				
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return senadoresActualesList
		}
	}
}