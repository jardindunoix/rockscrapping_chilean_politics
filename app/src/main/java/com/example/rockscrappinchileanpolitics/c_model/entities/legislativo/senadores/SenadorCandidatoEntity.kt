package com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.senadores

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "senador_candidato_table")
data class SenadorCandidatoEntity(
	@PrimaryKey(autoGenerate = true)
	var idSenadorCandidato:Int = 0, var nombre:String = "NOMBRE CHORRO", var apellido:String = "",
	var distrito:String = "", var partido:String = "", var paginaWeb:String = "WEB PAGE",
	var mail:String = "", var picture:String = "")
