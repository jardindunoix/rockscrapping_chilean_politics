package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Entity(tableName = StaticUtils.CONS_ACT_DET_TABLE)
data class ConsejalActualDetallePojo(
    @PrimaryKey(autoGenerate = true)
    var idConsejalActual: Int = 0,
    var nombre: String = "NOMBRE CHORRO",
    var apellido: String = "", var distrito: String = "", var partido: String = "",
    var paginaWeb: String = "WEB PAGE", var mail: String = "", var picture: String = "",
    var comuna: String = "CAMARONES"
)
