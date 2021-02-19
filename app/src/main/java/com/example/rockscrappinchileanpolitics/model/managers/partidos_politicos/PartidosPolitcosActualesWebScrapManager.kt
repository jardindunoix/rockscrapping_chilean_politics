package com.example.rockscrappinchileanpolitics.model.managers.partidos_politicos

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.partidos_politicos.PartidosPoliticosWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity

class PartidosPolitcosActualesWebScrapManager(context : Context) {
	
	var allPartidosActuales =
			MutableLiveData<MutableList<PartidoPoliticoEntity>>(mutableListOf())
	
	init {
		allPartidosActuales.value =
				getPartidosActuales(context)
	}
	
	fun getPartidosActuales(context : Context) : MutableList<PartidoPoliticoEntity> {
		var loader : AsyncTask<Void , Void , ArrayList<PartidoPoliticoEntity>>? =
				null
		var list =
				mutableListOf(PartidoPoliticoEntity())
		loader =
				PartidosPoliticosWebScrap.LoadInitNews(AppCompatActivity())
		loader !!.execute()
		list =
				loader.get()
		return list
	}
}
