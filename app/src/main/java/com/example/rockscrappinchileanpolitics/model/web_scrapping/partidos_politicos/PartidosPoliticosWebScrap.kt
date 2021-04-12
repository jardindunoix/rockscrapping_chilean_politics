package com.example.rockscrappinchileanpolitics.model.web_scrapping.partidos_politicos

import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import java.io.IOException

class PartidosPoliticosWebScrap {
	
	companion object {
		
		private var partidosActualesList:MutableList<PartidoPoliticoEntity> = ArrayList()
		fun doInBackground():MutableList<PartidoPoliticoEntity> {
			try {
				partidosActualesList =
					RepositorioWebScrapCallss.getPartidosPoliticos()
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return partidosActualesList
		}
	}
}
