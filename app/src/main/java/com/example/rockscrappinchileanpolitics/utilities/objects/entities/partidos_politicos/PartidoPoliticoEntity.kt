package com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partido_politico")
data class PartidoPoliticoEntity(@PrimaryKey(autoGenerate = true)
var id:Int = 0,val nombre:String = "", val paginaWeb:String = "",
   val mail:String = "", var picture:String = "")
