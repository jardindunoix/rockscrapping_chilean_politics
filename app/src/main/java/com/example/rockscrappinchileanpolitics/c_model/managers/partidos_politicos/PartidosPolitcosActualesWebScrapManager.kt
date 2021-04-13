package com.example.rockscrappinchileanpolitics.c_model.managers.partidos_politicos

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.repositorio.RepositorioWebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.entities.partidos_politicos.PartidoPoliticoEntity
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
		return RepositorioWebScrapCall.getPartidosPoliticos()
	}
}
