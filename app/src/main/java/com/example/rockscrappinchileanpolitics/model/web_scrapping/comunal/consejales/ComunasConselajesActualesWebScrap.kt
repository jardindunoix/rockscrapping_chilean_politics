package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import android.util.Log
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import java.io.IOException

class ComunasConselajesActualesWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<ComunaConsejalActualEntity>>() {
	
	private var comunasList:ArrayList<ComunaConsejalActualEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ComunaConsejalActualEntity> {
		try {
			val url = StaticStrigns.URL_CONSEJALES_ACTUALES
			val document = Jsoup.connect(url).get()
			val h3Elements = document.select("div.col-md-12").select("a").eachText()
			
			for (element in h3Elements) {
				if (element != h3Elements[0] && element.isNotEmpty() && isAllCaps(element)) {
					comunasList.add(ComunaConsejalActualEntity(nombre = element,
						paginaWeb = "${StaticStrigns.URL_COMUNA_DETALLE}${element}"))
				}
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return comunasList
	}
	
	override fun onPostExecute(result:ArrayList<ComunaConsejalActualEntity>?) {
		
	}
	
	private fun isAllCaps(element:String?):Boolean {
		val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
		return regex.containsMatchIn(input = element.toString()).not()
	}
}

}