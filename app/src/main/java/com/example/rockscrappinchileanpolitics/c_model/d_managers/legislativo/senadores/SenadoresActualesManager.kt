package com.example.rockscrappinchileanpolitics.c_model.d_managers.legislativo.senadores

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.c_model.webscrap.WebScrapCall
import com.example.rockscrappinchileanpolitics.c_model.b_entities.SenadorActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SenadoresActualesManager {
	
	var allSenadoresActuales = MutableLiveData<MutableList<SenadorActualEntity>>(mutableListOf())
	
	init {
		CoroutineScope(Dispatchers.IO).launch {
			try {
				allSenadoresActuales.postValue(getSenadoresActuales())
			} catch (e:Exception) {
				Log.e("ERRORRRRR ---->", e.toString())
			}
		}
	}
	
	private fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		return WebScrapCall.getSenadoresActuales()
	}
}