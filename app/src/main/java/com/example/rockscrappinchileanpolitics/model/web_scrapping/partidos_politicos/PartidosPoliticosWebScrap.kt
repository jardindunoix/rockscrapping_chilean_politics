package com.example.rockscrappinchileanpolitics.model.web_scrapping.partidos_politicos

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.web_scrapping.RepositorioWebScrapCalls
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.TD_PARTIDOS_POLITICOS_CLASS
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.TH_TITULO_PARTIDOS_POLITICOS_CLASS
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_PARTIDOS_POLITICOS_1
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_PARTIDOS_POLITICOS_2
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class PartidosPoliticosWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<PartidoPoliticoEntity>>() {
	
	private var partidosActualesList:ArrayList<PartidoPoliticoEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<PartidoPoliticoEntity> {
		try {
			partidosActualesList =
				RepositorioWebScrapCalls.getPartidosPoliticos() as ArrayList<PartidoPoliticoEntity>
		} catch (e:IOException) {
			e.printStackTrace()
		}
		
		return partidosActualesList
	}
	
	override fun onPostExecute(result:ArrayList<PartidoPoliticoEntity>?) {
	
	}
}

}