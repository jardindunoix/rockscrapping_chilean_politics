package com.example.rockscrappinchileanpolitics.model.database.comunal_dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.services.static_strings.StaticUtils

@Dao
interface ConsejalesDao {
    @Query("SELECT * FROM ${StaticUtils.CONSEJALES_ACTUALES_DETALLE_TABLE}")
    fun getConsejalesList(): LiveData<MutableList<ConsejalActualDetalleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsejalesDetail(consejalActual: ConsejalActualDetalleEntity)

    @Delete
    suspend fun deleteConsejal(consejalActual: ConsejalActualDetalleEntity)

    @Update
    suspend fun updateConsejal(consejalActual: ConsejalActualDetalleEntity)
}