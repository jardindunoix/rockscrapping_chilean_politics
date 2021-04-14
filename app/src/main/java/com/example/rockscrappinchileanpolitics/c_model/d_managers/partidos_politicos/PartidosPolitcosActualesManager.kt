package com.example.rockscrappinchileanpolitics.c_model.d_managers.partidos_politicos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.PartidoPoliticoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosPolitcosActualesManager {
	
	var allPartidosActuales = MutableLiveData<MutableList<PartidoPoliticoEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			try {
				allPartidosActuales.postValue(getPartidosActuales())
			} catch (e:Exception) {
				Log.e("ERRORRRRR ---->", e.toString())
			}
		}
	}
	
	private fun getPartidosActuales():MutableList<PartidoPoliticoEntity> {
		return WebScrapCall.getPartidosPoliticos()
	}
}
