package com.example.rockscrappinchileanpolitics.model.web_scrapping.diputados

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.GRID
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.H4
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.HREF
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.IMG
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.SRC
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_DIPUTADOS_ACTUALES_ROOT
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.DIPUTADOS_END_POINT
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.DIPUTADOS
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.util.stream.Collectors

class DiputadosActualesWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<DiputadoActualEntity>>() {
	
	private var diputadosActualesList:ArrayList<DiputadoActualEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<DiputadoActualEntity> {
		try {
			val url = "${URL_DIPUTADOS_ACTUALES_ROOT}${DIPUTADOS_END_POINT}"
			val document:Document = Jsoup.connect(url).get()
			val articleElement:Elements = document.select("article.${GRID}")
			val h4Elements = articleElement.select(H4).eachText()
			val nameList = h4Elements.stream().collect(Collectors.toList()) as ArrayList
			val webpage = articleElement.select("a").eachAttr(HREF).stream()
				.collect(Collectors.toList()) as ArrayList
			val imagesList = articleElement.select(IMG).eachAttr(SRC).stream()
				.collect(Collectors.toList()) as ArrayList
			var countAttr = 0
			var countName = 0
			
			if (webpage.size / 3 == nameList.size) {
				var name:String
				var pagina:String
				var image:String
				
				for (f in imagesList) {
					name = nameList[countName].toString()
					pagina = "${URL_DIPUTADOS_ACTUALES_ROOT}${DIPUTADOS}${webpage[countAttr]}"
					image = "${URL_DIPUTADOS_ACTUALES_ROOT}${f}"
					diputadosActualesList.add(
						DiputadoActualEntity(nombre = name, paginaWeb = pagina, picture = image))
					countAttr += 3
					++ countName
				}
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return diputadosActualesList
	}
	
	override fun onPostExecute(result:ArrayList<DiputadoActualEntity>?) {
	
	}
}

}
