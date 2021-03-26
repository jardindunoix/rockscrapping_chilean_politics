package com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns

@Entity(tableName = StaticStrigns.COMUNAS_CONSEJALES_TABLE)
data class ComunaConsejalActualEntity(@PrimaryKey(autoGenerate = true)
var idComunaConsejalActual:Int = 0, var nombre:String = "COMUNA", var region:String = "REGION",
	var paginaWeb:String = "CAMARONES", var picture:String = "")