package com.example.rockscrappinchileanpolitics.model.managers.partidos_politicos

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.partidos_politicos.PartidosPoliticosWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PartidosPolitcosActualesWebScrapManager {
	
	var allPartidosActuales = MutableLiveData<MutableList<PartidoPoliticoEntity>>(mutableListOf())
	
	init {
		GlobalScope.launch {
			allPartidosActuales.postValue(getPartidosActuales())
		}
	}
	
	private fun getPartidosActuales():MutableList<PartidoPoliticoEntity> {
		val list:MutableList<PartidoPoliticoEntity>
		list = PartidosPoliticosWebScrap.loadInitNews()
		return list
	}
}
