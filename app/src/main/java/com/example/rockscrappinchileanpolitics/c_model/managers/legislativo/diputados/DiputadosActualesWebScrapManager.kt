package com.example.rockscrappinchileanpolitics.c_model.managers.legislativo.diputados

import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.repositorio.RepositorioWebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.diputados.DiputadoActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiputadosActualesWebScrapManager {
	
	var allDiputadosActuales = MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			allDiputadosActuales.postValue(getDiputadosActuales())
		}
	}
	
	private fun getDiputadosActuales():MutableList<DiputadoActualEntity> {
		return RepositorioWebScrapCall.getDiputadosActuales()
	}
}


