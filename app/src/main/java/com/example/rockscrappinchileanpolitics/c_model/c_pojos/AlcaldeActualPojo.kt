package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alcalde_actual_table")
data class AlcaldeActualPojo(@PrimaryKey(autoGenerate = true)
var idAlcaldeActual:Int = 0, var nombre:String = "NOMBRE CHORRO", var apellido:String = "",
	var distrito:String = "", var partido:String = "", var paginaWeb:String = "WEB PAGE",
	var mail:String = "", var picture:String = "")

