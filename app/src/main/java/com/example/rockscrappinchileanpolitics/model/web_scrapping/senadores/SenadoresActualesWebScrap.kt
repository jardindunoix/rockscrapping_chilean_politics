package com.example.rockscrappinchileanpolitics.model.web_scrapping.senadores

import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.senadores.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.CLASS_NAME_SENADORES_ACTUALES
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns.Companion.URL_SENADORES_ACTUALES
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class SenadoresActualesWebScrap {

    internal class LoadInitNews(var activity: AppCompatActivity?) :
        AsyncTask<Void, Void, ArrayList<SenadorActualEntity>>() {

        private var senadoresActualesList: ArrayList<SenadorActualEntity> = ArrayList()

        override fun doInBackground(vararg params: Void?): ArrayList<SenadorActualEntity> {
            try {
                val url = URL_SENADORES_ACTUALES
                val document: Document = Jsoup.connect(url).get()
                val divElement: Elements = document.select("div.${CLASS_NAME_SENADORES_ACTUALES}")
                val webpageList = divElement.select("a").eachAttr("href")
                val nameList = divElement.select("img").eachAttr("alt")

                var countAttr = 0
                var countName = 0
                var name: String
                var webpage: String

                for (f in nameList) {
                    name = nameList.get(countName).toString()
                    webpage = "https://senado.cl/${webpageList.get(countAttr)}"
                    senadoresActualesList.add(
                        SenadorActualEntity(
                            nombre = name,
                            paginaWeb = webpage
                        )
                    )
                    ++countAttr
                    ++countName
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
            Log.d("SALIDA ---->", "--->${senadoresActualesList}")
            return senadoresActualesList
        }

        override fun onPostExecute(result: ArrayList<SenadorActualEntity>?) {

        }
    }
}