package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import android.util.Log
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class ConselajesActualesWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<ConsejalActualEntity>>() {
	
	private var consejalesActualesList:ArrayList<ConsejalActualEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ConsejalActualEntity> {
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
	
	override fun onPostExecute(result:ArrayList<ConsejalActualEntity>?) {
	
	}
}
}