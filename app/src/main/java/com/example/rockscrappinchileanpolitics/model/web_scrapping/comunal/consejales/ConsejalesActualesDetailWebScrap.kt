package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import java.io.IOException

class ConsejalesActualesDetailWebScrap{
	companion object {
		
		private var consejalesList:MutableList<ConsejalActualDetalleEntity> = mutableListOf()
		fun doInBackground(comuna:String):MutableList<ConsejalActualDetalleEntity> {
			try {
				consejalesList = RepositorioWebScrapCallss.getConsejalesActualesDetail(comuna)
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return consejalesList
		}
		
	}
}