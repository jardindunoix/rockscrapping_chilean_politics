package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss.Companion.getComunasConsejalesActuales
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import java.io.IOException

class ComunasConselajesActualesWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<ComunaConsejalActualEntity>>() {
	
	private var comunasList:ArrayList<ComunaConsejalActualEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ComunaConsejalActualEntity> {
		try {
		comunasList = getComunasConsejalesActuales() as ArrayList<ComunaConsejalActualEntity>
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return comunasList
	}
	
	override fun onPostExecute(result:ArrayList<ComunaConsejalActualEntity>?) {
	
	}

}
}