package com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.diputados

import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import java.io.IOException

class DiputadosActualesWebScrap {
	
	companion object{
		private var diputadosActualesList:MutableList<DiputadoActualEntity> = mutableListOf()
		
		fun doInBackground():MutableList<DiputadoActualEntity> {
			try {
				diputadosActualesList =
					RepositorioWebScrapCallss.getDiputadosActuales()
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return diputadosActualesList
		}
	}
}
