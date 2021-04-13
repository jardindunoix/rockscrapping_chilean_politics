package com.example.rockscrappinchileanpolitics.c_model.d_managers.partidos_politicos

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.PartidoPoliticoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosPolitcosActualesWebScrapManager {
	
	var allPartidosActuales = MutableLiveData<MutableList<PartidoPoliticoEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allPartidosActuales.postValue(getPartidosActuales())
		}
	}
	
	private fun getPartidosActuales():MutableList<PartidoPoliticoEntity> {
		return WebScrapCall.getPartidosPoliticos()
	}
}
