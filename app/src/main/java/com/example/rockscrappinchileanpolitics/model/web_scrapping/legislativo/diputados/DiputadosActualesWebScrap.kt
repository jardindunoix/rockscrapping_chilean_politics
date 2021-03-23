package com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.diputados

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import java.io.IOException

class DiputadosActualesWebScrap { internal class LoadInitNews():
	AsyncTask<Void, Void, ArrayList<DiputadoActualEntity>>() {
	
	private var diputadosActualesList:ArrayList<DiputadoActualEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<DiputadoActualEntity> {
		try {
			diputadosActualesList =
				RepositorioWebScrapCallss.getDiputadosActuales() as ArrayList<DiputadoActualEntity>
			
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return diputadosActualesList
	}
	
	override fun onPostExecute(result:ArrayList<DiputadoActualEntity>?) {
	
	}
}

}
