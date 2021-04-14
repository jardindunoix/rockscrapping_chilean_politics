package com.example.rockscrappinchileanpolitics.c_model.d_managers.comunal.consejales

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ComunaConsejalActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComunasConsejalesActualesManager {
	
	var allComunasConsejales = MutableLiveData<MutableList<ComunaConsejalActualEntity>>()
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			try {
				allComunasConsejales.postValue(getAllConsejalesActuales())
			} catch (e:Exception) {
				Log.e("ERRORRRRR ---->", e.toString())
			}
		}
	}
	
	private fun getAllConsejalesActuales():MutableList<ComunaConsejalActualEntity> {
		return WebScrapCall.getComunasConsejalesActuales()
	}
}

