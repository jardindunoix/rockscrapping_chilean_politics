package com.example.rockscrappinchileanpolitics.utilities.services.interfaces_listeners

import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity

interface ListenerConsejalComunas {
	
	fun viewTouchedShort(position:Int, comunaObjeto:ComunaConsejalActualEntity)
	fun viewTouchedLong(position:Int,comunaObjeto:ComunaConsejalActualEntity)
}