package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diputado_candidato_table")
data class DiputadoCandidatoPojo(
	@PrimaryKey(autoGenerate = true)
	var idDiputadoActual:Int = 0, var nombre:String = "NOMBRE CHORRO", var apellido:String = "",
	var distrito:String = "", var partido:String = "", var paginaWeb:String = "WEB PAGE",
	var mail:String = "", var picture:String = "")
