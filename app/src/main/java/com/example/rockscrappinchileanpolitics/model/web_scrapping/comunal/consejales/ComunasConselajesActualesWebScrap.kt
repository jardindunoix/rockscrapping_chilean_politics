package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss.Companion.getComunasConsejalesActuales
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import java.io.IOException

class ComunasConselajesActualesWebScrap {
	
	companion object{
		private var comunasList:MutableList<ComunaConsejalActualEntity> = mutableListOf()
		
		fun doInBackground():MutableList<ComunaConsejalActualEntity> {
			try {
				comunasList = getComunasConsejalesActuales()
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return comunasList
		}
	}
}