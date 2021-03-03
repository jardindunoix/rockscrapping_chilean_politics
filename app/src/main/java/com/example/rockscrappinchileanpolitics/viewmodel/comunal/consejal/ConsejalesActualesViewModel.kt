package com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.database.PoliticsDao
import com.example.rockscrappinchileanpolitics.model.database.PoliticsDatabase
import com.example.rockscrappinchileanpolitics.model.managers.comunal.consejales.ConsejalesActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.model.managers.database_managers.PoliticsDatabaseManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsejalesActualesViewModel(application:Application):AndroidViewModel(application) {
	
	var consejalesActualesList = MutableLiveData<MutableList<ConsejalActualEntity>>(mutableListOf())
	private val database = PoliticsDatabase.getDatabase(getApplication())
	private var dao:PoliticsDao = database.getDao()
	
	init {
		if (consejalesActualesList.value.isNullOrEmpty()) {
			CoroutineScope(Dispatchers.IO).launch {
				getConsejalesActualesList()
			}
			
		}
	}
	
	private fun getConsejalesActualesList() {
		val listDDBB = PoliticsDatabaseManager(dao).allConsejalesActuales
		if (listDDBB.value == null) {
			consejalesActualesList = ConsejalesActualesWebScrapManager().allConsejales
			// Log.d("LISTAenCOROUTINE ---->", consejalesActualesList.value.toString())
		} else {
			consejalesActualesList.value = listDDBB.value
		}
		// CoroutineScope(Dispatchers.IO).launch {
		// 	dao.insertListConsejalActual(consejalesActualesList.value !!)
		// }
		viewModelScope.launch {
			consejalesActualesList
		}
	}
	
}
