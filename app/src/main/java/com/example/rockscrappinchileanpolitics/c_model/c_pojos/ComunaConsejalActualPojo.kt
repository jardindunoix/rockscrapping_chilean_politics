package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Entity(tableName = StaticUtils.COM_CONS_TABLE)
data class ComunaConsejalActualPojo(@PrimaryKey(autoGenerate = true)
var idComunaConsejalActual:Int = 0, var nombre:String = "COMUNA", var region:String = "REGION",
	var paginaWeb:String = "CAMARONES", var picture:String = "")