package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import android.util.Log
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import java.io.IOException

class ConselajesActualesWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<ConsejalActualEntity>>() {
	
	private var consejalesActualesList:ArrayList<ConsejalActualEntity> = ArrayList()
	private var comunasList:ArrayList<String> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ConsejalActualEntity> {
		//trae lista de consejales
		/*try {
			val url = StaticStrigns.URL_CONSEJALES_ACTUALES
			val document:Document = Jsoup.connect(url).get()
			val divElement = document.select("div.col-md-12")
			val h3Elements = divElement.select("li").eachText() as ArrayList
			
			for (element in h3Elements) {
				consejalesActualesList.add(ConsejalActualEntity(nombre = element))
			}
		} catch (e:IOException) {
			e.printStackTrace()
		}*/
		try {
			val url = StaticStrigns.URL_CONSEJALES_ACTUALES
			val document = Jsoup.connect(url).get()
			val divElement = document.select("div.col-md-12")
			val h3Elements = divElement.select("a").eachText() as ArrayList
			
			for (element in h3Elements) {
				if (element != h3Elements[0] && element.isNotEmpty()) {
					consejalesActualesList.add(ConsejalActualEntity(nombre = element))
					if (isAllCaps(element)) {
						comunasList.add(element)
					}
				}
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		Log.d("Message ---->", comunasList.toString())
		return consejalesActualesList
	}
	
	private fun isAllCaps(element:String?):Boolean {
		val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
		return regex.containsMatchIn(input = element.toString()).not()
	}
	
	override fun onPostExecute(result:ArrayList<ConsejalActualEntity>?) {
		
	}
}
}