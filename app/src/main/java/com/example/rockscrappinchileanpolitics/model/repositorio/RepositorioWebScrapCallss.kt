package com.example.rockscrappinchileanpolitics.model.repositorio

import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.stream.Collectors

class RepositorioWebScrapCallss {
    companion object {

        /*COMUNAL*/
        fun getConsejalesActualesDetail(comuna:String): MutableList<ConsejalActualDetalleEntity> {
            val consejalesList: MutableList<ConsejalActualDetalleEntity> = mutableListOf()
            val url = StaticStrigns.URL_COMUNA_DETALLE + comuna
            val document = Jsoup.connect(url).get()
            val h3Elements = document.select("div.img-thumbnail").eachAttr("alt")
            val pic = document.select("img").eachAttr("src")
            var counter = 1
            for (element in h3Elements) {
                consejalesList.add(
                    ConsejalActualDetalleEntity(
                        nombre = element,
                        picture = "https://www.cdch" + ".cl${pic[counter]}"
                    )
                )
                counter++
            }
            return consejalesList
        }

        fun getComunasConsejalesActuales(): MutableList<ComunaConsejalActualEntity> {
            val comunasList = mutableListOf<ComunaConsejalActualEntity>()
            val url = StaticStrigns.URL_CONSEJALES_ACTUALES
            val document = Jsoup.connect(url).get()
            val h3Elements = document.select("div.col-md-12").select("a").eachText()

            for (element in h3Elements) {
                if (element != h3Elements[0] && element.isNotEmpty() && isAllCaps(element)) {
                    comunasList.add(
                        ComunaConsejalActualEntity(
                            nombre = element,
                            paginaWeb = "${StaticStrigns.URL_COMUNA_DETALLE}${element}"
                        )
                    )
                }
            }
            return comunasList
        }

        /*LEGISLATIVO*/
        fun getDiputadosActuales(): MutableList<DiputadoActualEntity> {
            val diputadosActualesList = mutableListOf<DiputadoActualEntity>()
            val url =
                "${StaticStrigns.URL_DIPUTADOS_ACTUALES_ROOT}${StaticStrigns.DIPUTADOS_END_POINT}"
            val document: Document = Jsoup.connect(url).get()
            val articleElement: Elements = document.select("article.${StaticStrigns.GRID}")
            val h4Elements = articleElement.select(StaticStrigns.H4).eachText()
            val nameList = h4Elements.stream().collect(Collectors.toList()) as ArrayList
            val webpage = articleElement.select("a").eachAttr(StaticStrigns.HREF).stream()
                .collect(Collectors.toList()) as ArrayList
            val imagesList =
                articleElement.select(StaticStrigns.IMG).eachAttr(StaticStrigns.SRC).stream()
                    .collect(Collectors.toList()) as ArrayList
            var countAttr = 0
            var countName = 0

            if (webpage.size / 3 == nameList.size) {
                var name: String
                var pagina: String
                var image: String

                for (f in imagesList) {
                    name = nameList[countName].toString()
                    pagina =
                        "${StaticStrigns.URL_DIPUTADOS_ACTUALES_ROOT}${StaticStrigns.DIPUTADOS}${webpage[countAttr]}"
                    image = "${StaticStrigns.URL_DIPUTADOS_ACTUALES_ROOT}${f}"
                    diputadosActualesList.add(
                        DiputadoActualEntity(nombre = name, paginaWeb = pagina, picture = image)
                    )
                    countAttr += 3
                    ++countName
                }
            }
            return diputadosActualesList
        }

        fun getSenadoresActuales(): MutableList<SenadorActualEntity> {
            val senadoresActualesList = mutableListOf<SenadorActualEntity>()
            val url = StaticStrigns.URL_SENADORES_ACTUALES
            val document: Document = Jsoup.connect(url).get()
            val divElement: Elements =
                document.select("div.${StaticStrigns.CLASS_NAME_SENADORES_ACTUALES}")
            val webpageList = divElement.select("a").eachAttr(StaticStrigns.HREF_WEB_PAGE_SENADORES)
            val nameList = divElement.select("img").eachAttr(StaticStrigns.NAME_SENADORES_ALT_LIST)
            val imagesList = divElement.select("img").eachAttr(StaticStrigns.IMAGES_LIST_SRC)
            var countAttr = 0
            var countName = 0
            var name: String
            var webpage: String
            var image: String

            for (f in imagesList) {
                name = nameList[countName].toString()
                webpage = "${StaticStrigns.URL_SENADORES_ACTUALES_ROOT}${webpageList[countAttr]}"
                image = "${StaticStrigns.URL_SENADORES_ACTUALES_ROOT}${f}"
                senadoresActualesList.add(
                    SenadorActualEntity(nombre = name, paginaWeb = webpage, picture = image)
                )
                ++countAttr
                ++countName
            }
            return senadoresActualesList
        }

        /*PARTIDOS POLITICOS*/
        fun getPartidosPoliticos(): MutableList<PartidoPoliticoEntity> {
            val partidosActualesList = mutableListOf<PartidoPoliticoEntity>()
            val url_1 = StaticStrigns.URL_PARTIDOS_POLITICOS_1
            val url_2 = StaticStrigns.URL_PARTIDOS_POLITICOS_2
            val pagina1Document: Document = Jsoup.connect(url_1).get()
            val pagina2Document: Document = Jsoup.connect(url_2).get()
            val element1 = pagina1Document.select(
                "${StaticStrigns.TD_PARTIDOS_POLITICOS_CLASS}.${StaticStrigns.TH_TITULO_PARTIDOS_POLITICOS_CLASS}"
            )
                .eachText() as ArrayList
            val element2 = pagina2Document.select(
                "${StaticStrigns.TD_PARTIDOS_POLITICOS_CLASS}.${StaticStrigns.TH_TITULO_PARTIDOS_POLITICOS_CLASS}"
            )
                .eachText() as ArrayList
            var countName = 0
            for (r in element1) {
                if (countName > 0) {
                    partidosActualesList.add(PartidoPoliticoEntity(nombre = r))
                }
                countName++
            }

            for (r in element2) {
                partidosActualesList.add(PartidoPoliticoEntity(nombre = r))
            }


            return partidosActualesList
        }

        /*GOBIERNO*/
        /*intendente*/
        /*gobernador*/
        /**/
        /*METODO PARA LA LISTA DE COMUNAS DE CONSEJALES*/
        private fun isAllCaps(element: String?): Boolean {
            val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
            return regex.containsMatchIn(input = element.toString()).not()
        }
    }
}