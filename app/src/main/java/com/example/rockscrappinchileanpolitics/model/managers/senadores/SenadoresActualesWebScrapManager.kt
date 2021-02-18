package com.example.rockscrappinchileanpolitics.model.managers.senadores

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.diputados.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.model.web_scrapping.senadores.SenadoresActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.senadores.SenadorActualEntity

class SenasoresActualesWebScrapManager(context : Context) {
	
	var allSenadoresActuales =
		MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		allSenadoresActuales.value = getSenadoresActuales(context)
	}
	
	fun getSenadoresActuales(context : Context) : MutableList<SenadorActualEntity> {
		var list = mutableListOf<SenadorActualEntity>()
		var loader : AsyncTask<Void , Void , ArrayList<SenadorActualEntity>>? = null
		list = mutableListOf(SenadorActualEntity())
		loader = SenadoresActualesWebScrap.LoadInitNews(AppCompatActivity())
		loader !!.execute()
		list = loader.get()
		return list
	}
}