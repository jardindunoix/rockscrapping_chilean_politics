package com.example.rockscrappinchileanpolitics.model.web_scrapping.diputados

import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.CLASS_NAME_DIPUTADOS_ACTUAlES
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.util.stream.Collectors

class DiputadosActualesWebScrap {
	internal class LoadInitNews(var activity : AppCompatActivity?) : AsyncTask<Void , Void , ArrayList<DiputadoActualEntity>>() {
	
	private var diputadosActualesList : ArrayList<DiputadoActualEntity> =
		ArrayList()
	
	override fun doInBackground(vararg params : Void?) : ArrayList<DiputadoActualEntity> {
		try {
			val url =
				StaticStrigns.URL_DIPUTADOS_ACTUAlES
			val document : Document =
				Jsoup.connect(url)
						.get()
			val articleElement : Elements =
				document.select("article.${CLASS_NAME_DIPUTADOS_ACTUAlES}")
			val h4Elements =
				articleElement.select("h4")
						.eachText()
			val nameList =
				h4Elements.stream()
						.collect(Collectors.toList()) as ArrayList
			val webpage =
				articleElement.select("a")
						.eachAttr("href")
						.stream()
						.collect(Collectors.toList()) as ArrayList
			val imagesList =
				articleElement.select("img")
						.eachAttr("src")
						.stream()
						.collect(Collectors.toList()) as ArrayList
			var countAttr =
				0
			var countName =
				0
			
			if (webpage.size / 3 == nameList.size) {
				var name : String
				var pagina : String
				var image : String
				
				for (f in imagesList) {
					name =
						nameList.get(countName)
								.toString()
					pagina =
						"https://www.camara.cl/diputados/${webpage.get(countAttr)}"
					image =
						"https://www.camara.cl${f}"
					// image = "https://www.camara.cl/img.aspx?prmID=GRCL1008"
					diputadosActualesList.add(DiputadoActualEntity(nombre = name ,
						paginaWeb = pagina ,
						picture = image))
					countAttr =
						countAttr + 3
					++ countName
				}
			} else {
				//	ver un  else porsiaca
			}
			
		} catch (e : IOException) {
			e.printStackTrace()
		}
		/*  Log.d("SALIDA ---->", "--->${diputadosActualesList.size}")*/
		return diputadosActualesList
	}
	
	override fun onPostExecute(result : ArrayList<DiputadoActualEntity>?) {
	
	}
}

}
