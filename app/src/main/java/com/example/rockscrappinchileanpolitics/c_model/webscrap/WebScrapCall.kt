package com.example.rockscrappinchileanpolitics.c_model.webscrap

import android.util.Log
import com.example.rockscrappinchileanpolitics.c_model.b_entities.*
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.comunaConvertForWebPage
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.firstLetter
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.isAllCapsUp
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.stream.Collectors

class WebScrapCall { companion object {
	
	fun getGallery():MutableList<GalleryEntity> {
		val galleryList = mutableListOf<GalleryEntity>()
		val url = StaticUtils.HEADER_BADGES_URL
		val document = Jsoup.connect(url).get()
		val result = document.select("img.img-responsive").eachAttr("src")
		
		for (badge in result) {
			galleryList.add(GalleryEntity(webPictureSite = badge.toString()))
		}
		return galleryList
	}
	
	/*COMUNAL*/
	fun getComunasConsejalesActuales():MutableList<ComunaConsejalActualEntity> {
		val comunasList = mutableListOf<ComunaConsejalActualEntity>()
		val url = StaticUtils.URL_CONSEJALES_ACTUALES
		val document = Jsoup.connect(url).get()
		/*trae el cuerpo del documento*/
		// val container = document.select("div.containerCDC")
		val listComunasElementsPRE = document.select("div.col-md-12").select("a").eachText()
		val listRegiones = document.select("div.text-center").select("h3").eachText()
		val listComunasElementsPOST = mutableListOf<String>()
		
		for (comu in listComunasElementsPRE) {
			if (comu != listComunasElementsPRE[0] && comu.isNotEmpty() && isAllCapsUp(comu)) {
				listComunasElementsPOST.add(comu)
			}
		}
		val floor = listComunasElementsPOST.size - 1
		
		for ((i, _) in listComunasElementsPOST.withIndex()) {
			val oldValueOne = "Región"
			val oldValueTwo = "DE"
			val oldValueThree = "L "
			val newValue = ""
			var indiceRegiones = 0
			if ((i + 1) <= floor) {
				val a = firstLetter(listComunasElementsPOST[i])
				val b = firstLetter(listComunasElementsPOST[(i + 1)])
				if (a <= b) {
					comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[i],
						region = listRegiones[indiceRegiones].replace(oldValueOne, newValue)
							.replace(oldValueTwo, newValue).replace(oldValueThree, newValue),
						paginaWeb = "${StaticUtils.URL_COMUNA_DETALLE}${
							comunaConvertForWebPage(listComunasElementsPOST[i])
						}"))
				} else {
					comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[i],
						region = listRegiones[indiceRegiones].replace(oldValueOne, newValue)
							.replace(oldValueTwo, newValue).replace(oldValueThree, newValue),
						paginaWeb = "${StaticUtils.URL_COMUNA_DETALLE}${listComunasElementsPOST[i]}"))
					++ indiceRegiones
				}
			}
		}
		val oldValueOne = "Región DE"
		val newValue = ""
		comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[floor],
			region = listRegiones[listRegiones.size - 1].replace(oldValueOne, newValue),
			paginaWeb = "${StaticUtils.URL_COMUNA_DETALLE}${
				comunaConvertForWebPage(listComunasElementsPOST[floor])
			}"))
		Log.e("Message ---->", comunasList.toString())
		return comunasList
	}
	
	fun getConsejalesActualesDetail(comuna:String):MutableList<ConsejalActualDetalleEntity> {
		val consejalesList:MutableList<ConsejalActualDetalleEntity> = mutableListOf()
		val url = StaticUtils.URL_COMUNA_DETALLE + comuna
		val document = Jsoup.connect(url).get()
		val listComunasElements = document.select("div.img-thumbnail").eachAttr("alt")
		val listPictureConsejales = document.select("img").eachAttr("src")
		// var counter = 1
		for ((i, element) in listComunasElements.withIndex()) {
			consejalesList.add(ConsejalActualDetalleEntity(nombre = element, picture = "${
				StaticUtils.URL_BASE_CONSEJALES_ACTUALES
			}${listPictureConsejales[i + 1]}"))
		}
		return consejalesList
	}
	
	/*LEGISLATIVO*/
	fun getDiputadosActuales():MutableList<DiputadoActualEntity> {
		val diputadosActualesList = mutableListOf<DiputadoActualEntity>()
		val url = "${StaticUtils.URL_DIPUTADOS_ACTUALES_ROOT}${StaticUtils.DIPUTADOS_END_POINT}"
		val document:Document = Jsoup.connect(url).get()
		val articleElement:Elements = document.select("article.${StaticUtils.GRID}")
		val h4Elements = articleElement.select(StaticUtils.H4).eachText()
		val nameList = h4Elements.stream().collect(Collectors.toList()) as ArrayList
		val webpage = articleElement.select("a").eachAttr(StaticUtils.HREF).stream()
			.collect(Collectors.toList()) as ArrayList
		val imagesList = articleElement.select(StaticUtils.IMG).eachAttr(StaticUtils.SRC).stream()
			.collect(Collectors.toList()) as ArrayList
		var countAttr = 0
		if (webpage.size / 3 == nameList.size) {
			val oldValueOne = "Sr. "
			val oldValueTwo = "Sra. "
			val newValue = ""
			
			for ((i, f) in imagesList.withIndex()) {
				diputadosActualesList.add(DiputadoActualEntity(
					nombre = nameList[i].replace(oldValueOne, newValue)
						.replace(oldValueTwo, newValue),
					paginaWeb = "${StaticUtils.URL_DIPUTADOS_ACTUALES_ROOT}${StaticUtils.DIPUTADOS}${webpage[countAttr]}",
					picture = "${StaticUtils.URL_DIPUTADOS_ACTUALES_ROOT}${f}"))
				countAttr += 3
			}
		}
		return diputadosActualesList
	}
	
	fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		val senadoresActualesList = mutableListOf<SenadorActualEntity>()
		val url = StaticUtils.URL_SENADORES_ACTUALES
		val document:Document = Jsoup.connect(url).get()
		val divElement:Elements =
			document.select("div.${StaticUtils.CLASS_NAME_SENADORES_ACTUALES}")
		val webpageList = divElement.select("a").eachAttr(StaticUtils.HREF_WEB_PAGE_SENADORES)
		val nameList = divElement.select("img").eachAttr(StaticUtils.NAME_SENADORES_ALT_LIST)
		val imagesList = divElement.select("img").eachAttr(StaticUtils.IMAGES_LIST_SRC)
		
		imagesList.withIndex().forEach { (index, f) ->
			senadoresActualesList.add(SenadorActualEntity(nombre = nameList[index].toString(),
				paginaWeb = "${StaticUtils.URL_SENADORES_ACTUALES_ROOT}${webpageList[index]}",
				picture = "${StaticUtils.URL_SENADORES_ACTUALES_ROOT}${f}"))
		}
		return senadoresActualesList
	}
	
	fun getPartidosPoliticos():MutableList<PartidoPoliticoEntity> {
		val partidosActualesList = mutableListOf<PartidoPoliticoEntity>()
		val urlOne = StaticUtils.URL_PARTIDOS_POLITICOS_1
		val urlTwo = StaticUtils.URL_PARTIDOS_POLITICOS_2
		val pagina1Document:Document = Jsoup.connect(urlOne).get()
		val pagina2Document:Document = Jsoup.connect(urlTwo).get()
		val excluyeFila = "Fecha constitución Partidos Políticos por región"
		val element1 = pagina1Document.select(
			"${StaticUtils.TD_PARTIDOS_POLITICOS_CLASS}.${StaticUtils.TH_TITULO_PARTIDOS_POLITICOS_CLASS}")
			.eachText()
		val element2 = pagina2Document.select(
			"${StaticUtils.TD_PARTIDOS_POLITICOS_CLASS}.${StaticUtils.TH_TITULO_PARTIDOS_POLITICOS_CLASS}")
			.eachText()
		
		for (r in element1) {
			if (! r.equals(excluyeFila, true)) {
				partidosActualesList.add(PartidoPoliticoEntity(
					nombre = r,
				))
			}
		}
		
		for (r in element2) {
			partidosActualesList.add(PartidoPoliticoEntity(
				nombre = r,
			))
		}
		return partidosActualesList
	}
}
}