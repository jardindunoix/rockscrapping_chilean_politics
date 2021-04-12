package com.example.rockscrappinchileanpolitics.model.database.partidos_politicos_dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.services.static_strings.StaticUtils

@Dao
interface PartidosPoliticosDao {
	
	@Query("SELECT * FROM ${StaticUtils.PARTIDOS_POLITICOS_TABLE}")
	fun getPartidosList():LiveData<MutableList<PartidoPoliticoEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPartidosDetail(consejalActual:PartidoPoliticoEntity)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPartidosLista(consejalActualList:MutableList<PartidoPoliticoEntity>)
	
	@Delete
	suspend fun deletePartido(consejalActual:PartidoPoliticoEntity)
	
	@Update
	suspend fun updatePartido(consejalActual:PartidoPoliticoEntity)
}
