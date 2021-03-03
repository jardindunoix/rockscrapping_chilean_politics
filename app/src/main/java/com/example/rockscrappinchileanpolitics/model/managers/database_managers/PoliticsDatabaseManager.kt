package com.example.rockscrappinchileanpolitics.model.managers.database_managers

import androidx.lifecycle.LiveData
import com.example.rockscrappinchileanpolitics.model.database.PoliticsDao
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity

class PoliticsDatabaseManager(dao:PoliticsDao) {
	
	val allConsejalesActuales:LiveData<MutableList<ConsejalActualEntity>> =
		dao.getAllConsejalesDDBB()
	
}