package com.example.rockscrappinchileanpolitics.model.database.comunal_dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns

@Dao
interface ConsejalesDao {
    @Query("SELECT * FROM ${StaticStrigns.CONSEJALES_ACTUALES_DETALLE_TABLE}")
    fun getConsejalesList(): LiveData<MutableList<ConsejalActualDetalleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsejalesDetail(consejalActual: ConsejalActualDetalleEntity)

    @Delete
    suspend fun deleteConsejal(consejalActual: ConsejalActualDetalleEntity)

    @Update
    suspend fun updateConsejal(consejalActual: ConsejalActualDetalleEntity)
}