package com.example.rockscrappinchileanpolitics.model.web_scrapping.partidos_politicos

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_PARTIDOS_POLITICOS_1
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_PARTIDOS_POLITICOS_2
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class PartidosPoliticosWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<PartidoPoliticoEntity>>() {
	
	private var partidosActualesList:ArrayList<PartidoPoliticoEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<PartidoPoliticoEntity> {
		try {
			val url_1 = URL_PARTIDOS_POLITICOS_1
			val url_2 = URL_PARTIDOS_POLITICOS_2
			val pagina1Document:Document = Jsoup.connect(url_1).get()
			val pagina2Document:Document = Jsoup.connect(url_2).get()
			val element1 = pagina1Document.select("td.th-titulo").eachText() as ArrayList
			val element2 = pagina2Document.select("td.th-titulo").eachText() as ArrayList
			var countName = 0
			for (r in element1) {
				if (countName > 0) {
					partidosActualesList.add(PartidoPoliticoEntity(nombre = r))
				}
				countName ++
			}
			
			for (r in element2) {
				partidosActualesList.add(PartidoPoliticoEntity(nombre = r))
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		
		return partidosActualesList
	}
	
	override fun onPostExecute(result:ArrayList<PartidoPoliticoEntity>?) {
	
	}
}

}