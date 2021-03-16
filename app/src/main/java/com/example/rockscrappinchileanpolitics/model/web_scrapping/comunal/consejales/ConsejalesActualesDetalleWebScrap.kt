package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import android.util.Log
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import java.io.IOException

class ConsejalesActualesDetalleWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<ConsejalActualDetalleEntity>>() {
	
	private var consejalesList:ArrayList<ConsejalActualDetalleEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ConsejalActualDetalleEntity> {
		try {
			val url = StaticStrigns.URL_COMUNA_DETALLE + "CAMARONES"
			val document = Jsoup.connect(url).get()
			val h3Elements = document.select("div.img-thumbnail").eachAttr("alt")
			Log.d("CONSEJALES ---->", h3Elements.toString())
			for (element in h3Elements) {
				consejalesList.add(ConsejalActualDetalleEntity(nombre = element))
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		Log.d("CONSEJALES ---->", consejalesList.toString())
		Log.d("CONSEJALES ---->", consejalesList.size.toString())
		return consejalesList
	}
	
	override fun onPostExecute(result:ArrayList<ConsejalActualDetalleEntity>?) {
	
	}
	
}

}