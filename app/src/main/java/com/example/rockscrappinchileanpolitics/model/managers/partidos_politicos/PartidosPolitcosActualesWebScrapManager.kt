package com.example.rockscrappinchileanpolitics.model.managers.partidos_politicos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.repositorio.RepositorioWebScrapCallss
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import java.io.IOException
import java.lang.reflect.InvocationTargetException

class PartidosPolitcosActualesWebScrapManager {
	
	var allPartidosActuales = MutableLiveData<MutableList<PartidoPoliticoEntity>>(mutableListOf())
	
	init {
		allPartidosActuales.postValue(getPartidosActuales())
	}
	
	private fun getPartidosActuales():MutableList<PartidoPoliticoEntity> {
		var list = (mutableListOf<PartidoPoliticoEntity>())
		try {
			list = RepositorioWebScrapCallss.getPartidosPoliticos()
		} catch (e:IOException) {
			e.printStackTrace()
		} catch (e:InvocationTargetException) {
			Log.e("InvocationTargetException      ---->", e.toString())
		}
		return list
	}
}
