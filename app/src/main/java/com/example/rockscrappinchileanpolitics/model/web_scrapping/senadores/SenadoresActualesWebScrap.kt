package com.example.rockscrappinchileanpolitics.model.web_scrapping.senadores

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.senadores.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.CLASS_NAME_SENADORES_ACTUALES
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.HREF_WEB_PAGE_SENADORES
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.IMAGES_LIST_SRC
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.NAME_SENADORES_ALT_LIST
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_SENADORES_ACTUALES
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_SENADORES_ACTUALES_ROOT
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class SenadoresActualesWebScrap {
	
	internal class LoadInitNews():
		AsyncTask<Void, Void, ArrayList<SenadorActualEntity>>() {
		
		private var senadoresActualesList:ArrayList<SenadorActualEntity> = ArrayList()
		
		override fun doInBackground(vararg params:Void?):ArrayList<SenadorActualEntity> {
			try {
				val url = URL_SENADORES_ACTUALES
				val document:Document = Jsoup.connect(url).get()
				val divElement:Elements =
					document.select("div.${CLASS_NAME_SENADORES_ACTUALES}")
				val webpageList = divElement.select("a").eachAttr(HREF_WEB_PAGE_SENADORES)
				val nameList = divElement.select("img").eachAttr(NAME_SENADORES_ALT_LIST)
				val imagesList = divElement.select("img").eachAttr(IMAGES_LIST_SRC)
				var countAttr = 0
				var countName = 0
				var name:String
				var webpage:String
				var image:String
				
				for (f in imagesList) {
					name = nameList[countName].toString()
					webpage = "${URL_SENADORES_ACTUALES_ROOT}${webpageList[countAttr]}"
					image = "${URL_SENADORES_ACTUALES_ROOT}${f}"
					senadoresActualesList.add(
						SenadorActualEntity(nombre = name, paginaWeb = webpage, picture = image))
					++ countAttr
					++ countName
				}
				
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return senadoresActualesList
		}
		
		override fun onPostExecute(result:ArrayList<SenadorActualEntity>?) {
		
		}
	}
}