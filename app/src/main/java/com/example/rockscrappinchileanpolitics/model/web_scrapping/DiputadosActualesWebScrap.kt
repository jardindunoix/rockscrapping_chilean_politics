package com.example.rockscrappinchileanpolitics.model.web_scrapping

import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.util.stream.Collectors

class DiputadosActualesWebScrap {
    internal class LoadInitNews(var activity: AppCompatActivity?) :
        AsyncTask<Void, Void, ArrayList<DiputadoActualEntity>>() {

        private var diputadosActualesList: ArrayList<DiputadoActualEntity> = ArrayList()

        override fun doInBackground(vararg params: Void?): ArrayList<DiputadoActualEntity> {
            try {
                val url = StaticStrigns.URL_DIPUTADOS_ACTUAlES
                val document: Document = Jsoup.connect(url).get()
                val articleElement: Elements = document.select("article.grid-2")
                val h4Elements = articleElement.select("h4").eachText()
                val webpage = articleElement.select("a").eachAttr("href")
                val attrList = webpage.stream().collect(
                    Collectors.toList()
                )
                val nameList = h4Elements.stream().collect(
                    Collectors.toList()
                )

                var diputadoActual = DiputadoActualEntity()
                var diputadosActualesList = ArrayList<DiputadoActualEntity>()
                var countAttr = 3
                var countName = 0

//                if (attrList.size == nameList.size) {
                for (f in nameList) {
                    diputadoActual.nombre = nameList[countName].toString()
                    if (countAttr % 3 == 0) {
                        /*   diputadoActual.paginaWeb =
                               "https://www.camara.cl/diputados/${attrList.get(countAttr)}"*/
                    }
                    diputadosActualesList.add(diputadoActual)

                    ++countAttr
                    ++countName
                }
                Log.d(
                    "OBJETO ---->",
                    ":----> ${diputadosActualesList}"
                )
//}
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return diputadosActualesList
        }

        override fun onPostExecute(result: ArrayList<DiputadoActualEntity>?) {

        }
    }

}
