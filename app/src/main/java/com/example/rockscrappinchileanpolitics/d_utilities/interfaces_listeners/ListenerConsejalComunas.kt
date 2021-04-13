package com.example.rockscrappinchileanpolitics.d_utilities.interfaces_listeners

import com.example.rockscrappinchileanpolitics.c_model.b_entities.ComunaConsejalActualEntity

interface ListenerConsejalComunas {
	
	fun viewTouchedShort(position:Int, comunaObjeto:ComunaConsejalActualEntity)
	fun viewTouchedLong(position:Int, comunaObjeto:ComunaConsejalActualEntity)
}