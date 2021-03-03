package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class ConselajesActualesWebScrap {
	
	companion object {
		
		private var consejalesActualesList:ArrayList<ConsejalActualEntity> = ArrayList()
		
		fun loadInitNews():ArrayList<ConsejalActualEntity> {
			try {
				val url = StaticStrigns.URL_CONSEJALES_ACTUALES
				val document:Document = Jsoup.connect(url).get()
				val divElement = document.select("div.col-md-12")
				val h3Elements = divElement.select("li").eachText() as ArrayList
				
				for (element in h3Elements) {
					consejalesActualesList.add(ConsejalActualEntity(nombre = element))
				}
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return consejalesActualesList
		}
		
	}
}
