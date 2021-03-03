package com.example.rockscrappinchileanpolitics.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity

@Dao
interface PoliticsDao {
	
	@Query("select * from consejal_actual")
	fun getAllConsejalesDDBB():LiveData<MutableList<ConsejalActualEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertListConsejalActual(list:MutableList<ConsejalActualEntity>)
}