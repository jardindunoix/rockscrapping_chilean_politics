package com.example.rockscrappinchileanpolitics.model.web_scrapping.partidos_politicos

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import java.io.IOException

class PartidosPoliticosWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<PartidoPoliticoEntity>>() {
	
	private var partidosActualesList:ArrayList<PartidoPoliticoEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<PartidoPoliticoEntity> {
		try {
			partidosActualesList =
				RepositorioWebScrapCallss.getPartidosPoliticos() as ArrayList<PartidoPoliticoEntity>
		} catch (e:IOException) {
			e.printStackTrace()
		}
		
		return partidosActualesList
	}
	
	override fun onPostExecute(result:ArrayList<PartidoPoliticoEntity>?) {
	
	}
}

}