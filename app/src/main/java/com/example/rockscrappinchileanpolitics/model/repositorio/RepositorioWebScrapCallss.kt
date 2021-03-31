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
        fun getComunasConsejalesActuales(): MutableList<ComunaConsejalActualEntity> {
            val comunasList = mutableListOf<ComunaConsejalActualEntity>()
            val url = StaticStrigns.URL_CONSEJALES_ACTUALES
            val document = Jsoup.connect(url).get()
            /*trae el cuerpo del documento*/
            // val container = document.select("div.containerCDC")
            val listComunasElementsPRE = document.select("div.col-md-12").select("a").eachText()
            val listRegionesElements = document.select("div.text-center").select("h3").eachText()
            val listComunasElementsPOST = mutableListOf<String>()

            for (comu in listComunasElementsPRE) {
                if (comu != listComunasElementsPRE[0] && comu.isNotEmpty() && isAllCapsUp(comu)) {
                    listComunasElementsPOST.add(comu)
                }
            }

            var indiceRegiones = 0
            var counterParaPrueba = 1
            val floor = 344
            for ((i, _) in listComunasElementsPOST.withIndex()) {
                val oldValueOne = "Región"
                val oldValueTwo = "DE"
                val oldValueThree = "L "
                val newValue = ""
                val regionCorregidaOne =
                    listRegionesElements[indiceRegiones].replace(oldValueOne, newValue)
                val regionCorregidaTwo = regionCorregidaOne.replace(oldValueTwo, newValue)
                val regionCorregidaThree = regionCorregidaTwo.replace(oldValueThree, newValue)
                if (counterParaPrueba <= floor) {
                    val a = firstLetter(listComunasElementsPOST[i])
                    val b = firstLetter(listComunasElementsPOST[counterParaPrueba])
                    ++counterParaPrueba
                    if (a <= b) {
                        comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[i],
                            region = regionCorregidaThree,
                            paginaWeb = "${StaticStrigns.URL_COMUNA_DETALLE}${listComunasElementsPOST[i]}"))
                    } else {
                        comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[i],
                            region = regionCorregidaThree,
                            paginaWeb = "${StaticStrigns.URL_COMUNA_DETALLE}${listComunasElementsPOST[i]}"))
                        ++indiceRegiones
                    }
                }
            }
            val oldValueOne = "Región DE"
            val newValue = ""
            val regionCorregida =
                listRegionesElements[indiceRegiones].replace(oldValueOne, newValue)
            comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[floor],
                region = regionCorregida,
                paginaWeb = "${StaticStrigns.URL_COMUNA_DETALLE}${listComunasElementsPOST[floor]}"))

            return comunasList
        }

        fun getConsejalesActualesDetail(comuna: String): MutableList<ConsejalActualDetalleEntity> {
            val consejalesList: MutableList<ConsejalActualDetalleEntity> = mutableListOf()
            val url = StaticStrigns.URL_COMUNA_DETALLE + comuna
            val document = Jsoup.connect(url).get()
            val listComunasElements = document.select("div.img-thumbnail").eachAttr("alt")
            val listPictureConsejales = document.select("img").eachAttr("src")
            var counter = 1
            for (element in listComunasElements) {
                consejalesList.add(ConsejalActualDetalleEntity(nombre = element,
                    picture = "https://www.cdch" + ".cl${listPictureConsejales[counter]}"))
                counter++
            }
            return consejalesList
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
                val oldValueOne = "Sr. "
                val oldValueTwo = "Sra. "
                val newValue = ""
                var nombreCorregido: String
                var nombre: String
                for (f in imagesList) {
                    nombre = nameList[countName].replace(oldValueOne, newValue)
                    nombreCorregido = nombre.replace(oldValueTwo, newValue)
                    diputadosActualesList.add(
                        DiputadoActualEntity(nombre = nombreCorregido,
                            paginaWeb = "${StaticStrigns.URL_DIPUTADOS_ACTUALES_ROOT}${StaticStrigns.DIPUTADOS}${webpage[countAttr]}",
                            picture = "${StaticStrigns.URL_DIPUTADOS_ACTUALES_ROOT}${f}"))
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
                    SenadorActualEntity(nombre = name, paginaWeb = webpage, picture = image))
                ++countAttr
                ++countName
            }
            return senadoresActualesList
        }

        /*PARTIDOS POLITICOS*/
        fun getPartidosPoliticos(): MutableList<PartidoPoliticoEntity> {
            val partidosActualesList = mutableListOf<PartidoPoliticoEntity>()
            val urlOne = StaticStrigns.URL_PARTIDOS_POLITICOS_1
            val urlTwo = StaticStrigns.URL_PARTIDOS_POLITICOS_2
            val pagina1Document: Document = Jsoup.connect(urlOne).get()
            val pagina2Document: Document = Jsoup.connect(urlTwo).get()
            /*cambiar por fuente*/
            val pictureSpecial =
                "https://partidorepublicanodechile.cl/wp-content/uploads/2020/03/ESCUDO-PR-PROTECCION-WHITE.png"
            val pictureSpecialTwo =
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Partido_Comunista_de_Chile-2.svg/739px-Partido_Comunista_de_Chile-2.svg.png"
            val excluyeFila = "Fecha constitución Partidos Políticos por región"

            /*****************************/
            val element1 = pagina1Document.select(
                "${StaticStrigns.TD_PARTIDOS_POLITICOS_CLASS}.${StaticStrigns.TH_TITULO_PARTIDOS_POLITICOS_CLASS}")
                .eachText() as ArrayList
            val element2 = pagina2Document.select(
                "${StaticStrigns.TD_PARTIDOS_POLITICOS_CLASS}.${StaticStrigns.TH_TITULO_PARTIDOS_POLITICOS_CLASS}")
                .eachText() as ArrayList

            for (r in element1) {
                if (!r.equals(excluyeFila, true)) {
                    partidosActualesList.add(PartidoPoliticoEntity(nombre = r,
                        picture = pictureSpecial))
                }
            }

            for (r in element2) {
                partidosActualesList.add(PartidoPoliticoEntity(nombre = r,
                    picture = pictureSpecialTwo))
            }
            return partidosActualesList
        }

        /**************************************************************************************************/
        /*METODOS*/
        private fun isAllCapsUp(element: String?): Boolean {
            val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
            return regex.containsMatchIn(input = element.toString()).not()
        }

        private fun firstLetter(word: String): Int {
            val letters = word.trim().split("")
            val letterOne = letters[1]
            return letterToNumber(letterOne)
        }

        private fun letterToNumber(letter: String): Int {
            val oldValue = " "
            val newValue = ""
            val listChar = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".replace(oldValue, newValue).split("")
            for ((i, char) in listChar.withIndex()) {
                when (char == letter.trim()) {
                    true -> return i
                }
            }
            return 0
        }
    }
}