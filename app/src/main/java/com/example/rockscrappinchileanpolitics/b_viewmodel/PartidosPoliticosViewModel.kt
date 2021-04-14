package com.example.rockscrappinchileanpolitics.b_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.c_model.b_entities.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.c_model.d_managers.partidos_politicos.PartidosPolitcosActualesManager
import kotlinx.coroutines.launch

class PartidosPoliticosViewModel(application:Application):AndroidViewModel(application) {
	
	var partidosActualesList = MutableLiveData<MutableList<PartidoPoliticoEntity>>()
	
	init {
			getPartidosActualesList()
	}
	
	private fun getPartidosActualesList() {
		partidosActualesList = PartidosPolitcosActualesManager().allPartidosActuales
		viewModelScope.launch {
			partidosActualesList
		}
	}
}