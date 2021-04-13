package com.example.rockscrappinchileanpolitics.c_model.entities.comunal.alcaldes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alcalde_candidato_table")
data class AlcaldeCandidatoEntity(@PrimaryKey(autoGenerate = true)
var idAlcaldeCadidarto:Int = 0, var nombre:String = "NOMBRE CHORRO", var apellido:String = "",
	var distrito:String = "", var partido:String = "", var paginaWeb:String = "WEB PAGE",
	var mail:String = "", var picture:String = "")
