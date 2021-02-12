package com.example.rockscrappinchileanpolitics.model.managers.diputados

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.diputados.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity

class DiputadosActualesWebScrapManager(context: Context) {

    var allDiputadosActuales =
        MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())

    init {
        allDiputadosActuales.value = getDiputadosActuales(context)
    }

    fun getDiputadosActuales(context: Context): MutableList<DiputadoActualEntity> {
        var list = mutableListOf<DiputadoActualEntity>()
        var loader: AsyncTask<Void, Void, ArrayList<DiputadoActualEntity>>? = null
        list = mutableListOf(DiputadoActualEntity())
        loader = DiputadosActualesWebScrap.LoadInitNews(AppCompatActivity())
        loader!!.execute()
        list = loader.get()
        return list
    }
}


