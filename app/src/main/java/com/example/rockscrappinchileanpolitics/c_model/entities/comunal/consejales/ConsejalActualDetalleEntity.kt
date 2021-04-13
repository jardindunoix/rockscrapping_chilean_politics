package com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Entity(tableName = StaticUtils.CONSEJALES_ACTUALES_DETALLE_TABLE)
data class ConsejalActualDetalleEntity(
    @PrimaryKey(autoGenerate = true)
    var idConsejalActual: Int = 0,
    var nombre: String = "NOMBRE CHORRO",
    var apellido: String = "", var distrito: String = "", var partido: String = "",
    var paginaWeb: String = "WEB PAGE", var mail: String = "", var picture: String = "",
    var comuna: String = "CAMARONES"
)