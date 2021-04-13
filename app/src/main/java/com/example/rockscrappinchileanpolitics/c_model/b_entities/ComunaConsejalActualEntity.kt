package com.example.rockscrappinchileanpolitics.c_model.b_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Entity(tableName = StaticUtils.COMUNAS_CONSEJALES_TABLE)
data class ComunaConsejalActualEntity(@PrimaryKey(autoGenerate = true)
var idComunaConsejalActual:Int = 0, var nombre:String = "COMUNA", var region:String = "REGION",
	var paginaWeb:String = "CAMARONES", var picture:String = "")