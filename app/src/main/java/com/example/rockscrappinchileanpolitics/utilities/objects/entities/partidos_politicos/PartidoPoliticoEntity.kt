package com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidos_politicos_table")
data class PartidoPoliticoEntity(
	@PrimaryKey(autoGenerate = true)
	var idPartidosPoliticos:Int = 0, var nombre:String = "", var paginaWeb:String = "",
	var mail:String = "", var picture:String = "")
