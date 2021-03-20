package com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.diputados

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.web_scrapping.RepositorioWebScrapCalls
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
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
			diputadosActualesList =
				RepositorioWebScrapCalls.getDiputadosActuales() as ArrayList<DiputadoActualEntity>
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return diputadosActualesList
	}
	
	override fun onPostExecute(result:ArrayList<DiputadoActualEntity>?) {
	
	}
}

}
