package com.example.rockscrappinchileanpolitics.viewmodel.partidos_politicos

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rockscrappinchileanpolitics.model.database.PoliticsDatabase
import com.example.rockscrappinchileanpolitics.model.managers.database_managers.PartidosPoliticosDBManager
import com.example.rockscrappinchileanpolitics.model.managers.partidos_politicos.PartidosPolitcosActualesWebScrapManager
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosPoliticosViewModel(application:Application):AndroidViewModel(application) {
	
	var partidosActualesList = MutableLiveData<MutableList<PartidoPoliticoEntity>>()
	private val dao = PoliticsDatabase.getDatabase(getApplication()).getDaoFromDatabase()
	private val databaseManager = PartidosPoliticosDBManager(dao)
	
	init {
		if (partidosActualesList.value.isNullOrEmpty()) {
			// CoroutineScope(Dispatchers.Default).launch {
			getPartidosActualesListFromWebScrap()
			// }
		} else {
			getPartidosActualesListFromDatabase()
		}
	}
	
	private fun getPartidosActualesListFromDatabase() {
		partidosActualesList.value = databaseManager.getAllPartidosFromManagerDDBB().value
	}
	
	private fun getPartidosActualesListFromWebScrap() {
		partidosActualesList = PartidosPolitcosActualesWebScrapManager().allPartidosActuales
		Log.e("FROM VIEWMODEL ---->", partidosActualesList.value.toString())
		// databaseManager.insertListInDDBB(partidosActualesList.value !!)
		viewModelScope.launch {
			partidosActualesList
		}
	}
}