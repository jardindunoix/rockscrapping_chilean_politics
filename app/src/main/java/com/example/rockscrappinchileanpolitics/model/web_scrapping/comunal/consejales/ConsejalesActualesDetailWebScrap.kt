package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import java.io.IOException

class ConsejalesActualesDetailWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<ConsejalActualDetalleEntity>?>() {
	
	private var comunasList:MutableList<ConsejalActualDetalleEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ConsejalActualDetalleEntity> {
		try {
			val url = StaticStrigns.URL_COMUNA_DETALLE + "vitacura"
			val document = Jsoup.connect(url).get()
			val h3Elements = document.select("div.col-md-12").select("a").eachText()
			var counter = 1
			for (element in h3Elements) {
				if (element != h3Elements[0] && element.isNotEmpty() && isAllCaps(element)) {
					comunasList.add(
						ConsejalActualDetalleEntity(
							nombre = element, picture = "${StaticStrigns.URL_COMUNA_DETALLE}${element}"
						)
					)
				}
				counter ++
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return comunasList as ArrayList<ConsejalActualDetalleEntity>
	}
	
	private fun isAllCaps(element:String?):Boolean {
		val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
		return regex.containsMatchIn(input = element.toString()).not()
	}
}
	
}