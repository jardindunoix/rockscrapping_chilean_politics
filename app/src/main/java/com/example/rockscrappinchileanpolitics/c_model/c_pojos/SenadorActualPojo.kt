package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "senador_actual_table")
data class SenadorActualPojo(
	@PrimaryKey(autoGenerate = true)
	var idSenadorActual:Int = 0, var nombre:String = "NOMBRE CHORRO", var apellido:String = "",
	var distrito:String = "", var partido:String = "", var paginaWeb:String = "WEB PAGE",
	var mail:String = "", var picture:String = "")
