package com.example.rockscrappinchileanpolitics.model.web_scrapping.legislativo.senadores

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.web_scrapping.RepositorioWebScrapCalls.Companion.getSenadoresActuales
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import java.io.IOException

class SenadoresActualesWebScrap {
	
	internal class LoadInitNews():AsyncTask<Void, Void, ArrayList<SenadorActualEntity>>() {
		
		private var senadoresActualesList:ArrayList<SenadorActualEntity> = ArrayList()
		
		override fun doInBackground(vararg params:Void?):ArrayList<SenadorActualEntity> {
			try {
				senadoresActualesList = getSenadoresActuales() as ArrayList<SenadorActualEntity>
				
			} catch (e:IOException) {
				e.printStackTrace()
			}
			return senadoresActualesList
		}
		
		override fun onPostExecute(result:ArrayList<SenadorActualEntity>?) {
		
		}
	}
}