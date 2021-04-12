package com.example.rockscrappinchileanpolitics.model.managers.database_managers

import androidx.lifecycle.LiveData
import com.example.rockscrappinchileanpolitics.model.database.partidos_politicos_dao.PartidosPoliticosDao
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity

class PartidosPoliticosDBManager(private val dao:PartidosPoliticosDao) {
	
	suspend fun insertListInDDBB(list:MutableList<PartidoPoliticoEntity>) {
		dao.insertPartidosLista(list)
	}
	
	fun getAllPartidosFromManagerDDBB():LiveData<MutableList<PartidoPoliticoEntity>> {
		return dao.getPartidosList()
	}
}