package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidos_politicos_table")
data class PartidoPoliticoPojo(
	@PrimaryKey(autoGenerate = true)
	var idPartidosPoliticos:Int = 0, var nombre:String = "", var paginaWeb:String = "",
	var mail:String = "", var picture:String = "")
