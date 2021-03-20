package com.example.rockscrappinchileanpolitics.model.web_scrapping.comunal.consejales

import android.os.AsyncTask
import com.example.rockscrappinchileanpolitics.model.web_scrapping.RepositorioWebScrapCalls
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import java.io.IOException

class ConsejalesActualesDetailWebScrap { internal class LoadInitNews:
	AsyncTask<Void, Void, ArrayList<ConsejalActualDetalleEntity>?>() {
	
	private var consejalesList:MutableList<ConsejalActualDetalleEntity> = ArrayList()
	
	override fun doInBackground(vararg params:Void?):ArrayList<ConsejalActualDetalleEntity> {
		try {
			consejalesList = RepositorioWebScrapCalls.getConsejalesActualesDetail()
		} catch (e:IOException) {
			e.printStackTrace()
		}
		return consejalesList as ArrayList<ConsejalActualDetalleEntity>
	}
}
}