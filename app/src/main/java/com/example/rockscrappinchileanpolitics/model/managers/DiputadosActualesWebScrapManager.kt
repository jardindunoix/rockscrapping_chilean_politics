package com.example.rockscrappinchileanpolitics.model.managers

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.rockscrappinchileanpolitics.model.web_scrapping.DiputadosActualesWebScrap
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class DiputadosActualesWebScrapManager(context: Context) {

    var allDiputadosActuales =
        MutableLiveData<MutableList<DiputadoActualEntity>>(mutableListOf())

    init {
        allDiputadosActuales.value = getDiputadosActuales(context)
    }

    fun getDiputadosActuales(context: Context): MutableList<DiputadoActualEntity> {
        var list = mutableListOf<DiputadoActualEntity>()
        var loader: AsyncTask<Void, Void, ArrayList<DiputadoActualEntity>>? = null

        loader = DiputadosActualesWebScrap.LoadInitNews(AppCompatActivity())
        loader!!.execute()
        list = mutableListOf(DiputadoActualEntity())
        return list
    }
}


