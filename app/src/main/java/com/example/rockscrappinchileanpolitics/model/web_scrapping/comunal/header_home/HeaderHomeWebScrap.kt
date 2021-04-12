package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.header_home

import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity
import java.io.IOException

class HeaderHomeWebScrap {
	
	companion object {
		
		private var comunasList:MutableList<HeaderHomeEntity> = mutableListOf()
		fun doInBackground():MutableList<HeaderHomeEntity> {
			try {
				comunasList =
					RepositorioWebScrapCallss.getHeaderBadges()
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return comunasList
		}
	}
}
