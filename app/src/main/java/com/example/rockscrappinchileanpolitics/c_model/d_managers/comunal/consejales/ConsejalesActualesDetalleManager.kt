package com.example.rockscrappinchileanpolitics.c_model.d_managers.comunal.consejales

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ConsejalActualDetalleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsejalesActualesDetalleManager(val comuna:String) {
	
	var allConsejales = MutableLiveData<MutableList<ConsejalActualDetalleEntity>>()
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			try {
				allConsejales.postValue(getAllConsejalesActuales(comuna))
			} catch (e:Exception) {
				Log.e("ERRORRRRR ---->", e.toString())
			}
		}
	}
	
	private fun getAllConsejalesActuales(comuna:String):MutableList<ConsejalActualDetalleEntity> {
		return WebScrapCall.getConsejalesActualesDetail(comuna)
	}
}

