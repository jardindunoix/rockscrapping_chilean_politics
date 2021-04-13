package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alcalde_candidato_table")
data class AlcaldeCandidatoPojo(@PrimaryKey(autoGenerate = true)
var idAlcaldeCadidarto:Int = 0, var nombre:String = "NOMBRE CHORRO", var apellido:String = "",
	var distrito:String = "", var partido:String = "", var paginaWeb:String = "WEB PAGE",
	var mail:String = "", var picture:String = "")
