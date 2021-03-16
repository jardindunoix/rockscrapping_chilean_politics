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
			val url = StaticStrigns.URL_COMUNA_DETALLE + "Vitacura"
			val document = Jsoup.connect(url).get()
			val h3Elements = document.select("div.img-thumbnail").eachAttr("alt")
			val pic = document.select("img").eachAttr("src")
			
			Log.d("CONSEJALES ---->", h3Elements.toString())
			var counter = 1
			for (element in h3Elements) {
				consejalesList.add(ConsejalActualDetalleEntity(nombre = element,
					picture = "https://www.cdch" + ".cl${pic[counter]}"))
				counter ++
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