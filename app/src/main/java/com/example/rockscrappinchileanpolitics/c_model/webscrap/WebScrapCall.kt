package com.example.rockscrappinchileanpolitics.c_model.webscrap

import com.example.rockscrappinchileanpolitics.c_model.b_entities.*
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.convertComunaForWebPage
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.erasePartido
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.firstLetter
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.isAllCapsUp
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.stream.Collectors

class WebScrapCall { companion object {
	
	fun getGallery():MutableList<GalleryEntity> {
		val galleryList = mutableListOf<GalleryEntity>()
		val url = StaticUtils.GALLERY_URL
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
		val url = StaticUtils.PPAL_URL_CONS_ACT
		val document = Jsoup.connect(url).get()
		/*trae el cuerpo del documento*/
		// val container = document.select("div.containerCDC")
		val listComunasElementsPRE = document.select("div.col-md-12").select("a").eachText()
		val listRegiones = document.select("div.text-center").select("h3").eachText()
		val listComunasElementsPOST = mutableListOf<String>()
		val listWebEndPoint = document.select("div.col-md-10").select("a").eachAttr("href")
		
		for (comu in listComunasElementsPRE) {
			if (comu != listComunasElementsPRE[0] && comu.isNotEmpty() && isAllCapsUp(comu)) {
				listComunasElementsPOST.add(comu)
			}
		}
		val floor = listComunasElementsPOST.size - 1
		var indiceRegiones = 0
		for ((i, _) in listComunasElementsPOST.withIndex()) {
			val oldValueOne = "Región"
			val oldValueTwo = "DE"
			val oldValueThree = "L "
			val newValue = ""
			if ((i + 1) <= floor) {
				val a = firstLetter(listComunasElementsPOST[i])
				val b = firstLetter(listComunasElementsPOST[(i + 1)])
				if (a <= b) {
					comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[i],
						region = listRegiones[indiceRegiones].replace(oldValueOne, newValue)
							.replace(oldValueTwo, newValue).replace(oldValueThree, newValue),
						paginaWeb = "${StaticUtils.URL_COM_DET}${
							convertComunaForWebPage(listComunasElementsPOST[i])
						}"))
				} else {
					comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[i],
						region = listRegiones[indiceRegiones].replace(oldValueOne, newValue)
							.replace(oldValueTwo, newValue).replace(oldValueThree, newValue),
						paginaWeb = "${StaticUtils.URL_COM_DET}${listComunasElementsPOST[i]}"))
					++ indiceRegiones
				}
			}
		}
		val oldValueOne = "Región DE"
		val newValue = ""
		comunasList.add(ComunaConsejalActualEntity(nombre = listComunasElementsPOST[floor],
			region = listRegiones[listRegiones.size - 1].replace(oldValueOne, newValue),
			paginaWeb = "${StaticUtils.URL_COM_DET}${
				convertComunaForWebPage(listComunasElementsPOST[floor])
			}"))
		return comunasList
	}
	
	fun getConsejalesActualesDetail(comuna:String):MutableList<ConsejalActualDetalleEntity> {
		val consejalesList:MutableList<ConsejalActualDetalleEntity> = mutableListOf()
		val url = StaticUtils.URL_COM_DET + comuna
		val document = Jsoup.connect(url).get()
		val listComunasElements = document.select("div.img-thumbnail").eachAttr("alt")
		val listPictureConsejales = document.select("img").eachAttr("src")
		// var counter = 1
		for ((i, element) in listComunasElements.withIndex()) {
			consejalesList.add(ConsejalActualDetalleEntity(nombre = element, picture = "${
				StaticUtils.BASE_URL_CONS_ACT
			}${listPictureConsejales[i + 1]}"))
		}
		return consejalesList
	}
	
	/*LEGISLATIVO*/
	fun getDiputadosActuales():MutableList<DiputadoActualEntity> {
		val diputadosActualesList = mutableListOf<DiputadoActualEntity>()
		val url = "${StaticUtils.BASE_URL_DIP_ACT}${StaticUtils.END_POINT_DIP_ACT}"
		val document:Document = Jsoup.connect(url).get()
		val articleElement:Elements = document.select("article.${StaticUtils.GRID_DIP_ACT}")
		val h4Elements = articleElement.select(StaticUtils.H4_DIP_ACT).eachText()
		val nameList = h4Elements.stream().collect(Collectors.toList())
		val webpage = articleElement.select("a").eachAttr(StaticUtils.HREF_DIP_ACT).stream()
			.collect(Collectors.toList())
		val imagesList =
			articleElement.select(StaticUtils.IMG_DIP_ACT).eachAttr(StaticUtils.SRC_DIP_ACT)
				.stream().collect(Collectors.toList())
		var countAttr = 0
		if (webpage.size / 3 == nameList.size) {
			val oldValueOne = "Sr. "
			val oldValueTwo = "Sra. "
			val newValue = ""
			
			for ((i, f) in imagesList.withIndex()) {
				diputadosActualesList.add(DiputadoActualEntity(
					nombre = nameList[i].replace(oldValueOne, newValue)
						.replace(oldValueTwo, newValue),
					paginaWeb = "${StaticUtils.BASE_URL_DIP_ACT}${StaticUtils.DIPUTADOS_DIP_ACT}${webpage[countAttr]}",
					picture = "${StaticUtils.BASE_URL_DIP_ACT}${f}"))
				countAttr += 3
			}
		}
		return diputadosActualesList
	}
	
	fun getSenadoresActuales():MutableList<SenadorActualEntity> {
		val senadoresActualesList = mutableListOf<SenadorActualEntity>()
		val url = StaticUtils.PPAL_URL_SEN_ACT
		val document:Document = Jsoup.connect(url).get()
		val divElement:Elements = document.select("div.${StaticUtils.CLASS_SEN_ACT}")
		val webpageList = divElement.select("a").eachAttr(StaticUtils.HREF_SEN_ACT)
		val nameList = divElement.select("img").eachAttr(StaticUtils.ALT_SEN_ACT)
		val imagesList = divElement.select("img").eachAttr(StaticUtils.SRC_SEN_ACT)
		
		imagesList.withIndex().forEach { (index, f) ->
			senadoresActualesList.add(SenadorActualEntity(nombre = nameList[index].toString(),
				paginaWeb = "${StaticUtils.BASE_URL_SEN_ACT}${webpageList[index]}",
				picture = "${StaticUtils.BASE_URL_SEN_ACT}${f}"))
		}
		return senadoresActualesList
	}
	
	fun getPartidosPoliticos():MutableList<PartidoPoliticoEntity> {
		val partidosActualesList = mutableListOf<PartidoPoliticoEntity>()
		val urlOne = StaticUtils.URL_PART_POL_1
		val urlTwo = StaticUtils.URL_PART_POL_2
		val pagina1Document:Document = Jsoup.connect(urlOne).get()
		val pagina2Document:Document = Jsoup.connect(urlTwo).get()
		val excluyeFila = "Fecha constitución Partidos Políticos por región"
		val element1 =
			pagina1Document.select("${StaticUtils.TD_PART_POL}.${StaticUtils.TH_TITULO_PART_POL}")
				.eachText()
		val element2 =
			pagina2Document.select("${StaticUtils.TD_PART_POL}.${StaticUtils.TH_TITULO_PART_POL}")
				.eachText()
		
		for (r in element1) {
			if (! r.equals(excluyeFila, true)) {
				partidosActualesList.add(PartidoPoliticoEntity(
					nombre = erasePartido(r),
				))
			}
		}
		
		for (r in element2) {
			partidosActualesList.add(PartidoPoliticoEntity(
				nombre = erasePartido(r),
			))
		}
		return partidosActualesList
	}
}
}