package com.example.rockscrappinchileanpolitics.viewmodel.partidos_politicos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.managers.partidos_politicos.PartidosPolitcosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosPoliticosViewModel(application:Application):AndroidViewModel(application) {
	
	var partidosActualesList = MutableLiveData<MutableList<PartidoPoliticoEntity>>()
	
	init {
		if (partidosActualesList.value.isNullOrEmpty()) {
			CoroutineScope(Dispatchers.IO).launch {
				getPartidosActualesList()
			}
		}
	}
	
	private fun getPartidosActualesList() {
		partidosActualesList = PartidosPolitcosActualesWebScrapManager().allPartidosActuales
		viewModelScope.launch {
			partidosActualesList
		}
	}
}