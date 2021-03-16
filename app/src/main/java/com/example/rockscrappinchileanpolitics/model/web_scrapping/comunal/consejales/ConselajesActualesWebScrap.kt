package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import android.util.Log
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import java.io.IOException

class ConselajesActualesWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<String>>() {
	
	private var comunasList:ArrayList<String> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<String> {
		try {
			val url = StaticStrigns.URL_CONSEJALES_ACTUALES
			val document = Jsoup.connect(url).get()
			val h3Elements = document.select("div.col-md-12").select("a").eachText()
			
			for (element in h3Elements) {
				if (element != h3Elements[0] && element.isNotEmpty() && isAllCaps(element)) {
					comunasList.add(
						(element)
					)
				}
			}
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		Log.d("Message ---->", comunasList.toString())
		Log.d("Message ---->", comunasList.size.toString())
		return comunasList.sorted().toMutableList() as ArrayList<String>
	}
	
	override fun onPostExecute(
		result:ArrayList<String>?
	) {
		
	}
	
	private fun isAllCaps(element:String?):Boolean {
		val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
		return regex.containsMatchIn(input = element.toString()).not()
	}
}

}