package com.example.rockscrappinchileanpolitics.utilities.services

class StaticStrigns { companion object {
	
	/*database name*/
	const val DATABASE_NAME = "rockscrap_chilean_politics"
	
	/*table names*/
	const val PARTIDO_POLITICO = "partido_politico"
	const val CONSEJAL_ACTUAL = "consejal_actual"
	const val CONSEJAL_CANDIDATO = "consejal_candidato"
	const val DIPUTADO_ACTUAL = "diputado_actual"
	const val DIPUTADO_CANDIDATO = "diputado_candidato"
	const val SENADOR_ACTUAL = "senador_actual"
	const val SENADOR_CANDIDATO = "senador_candidato"
	const val ALCALDE_ACTUAL = "alcalde_actual"
	const val ALCALDE_CANDIDATO = "alcalde_candidato"
	
	/*constantes diputados actuales*/
	const val URL_DIPUTADOS_ACTUALES_ROOT = "https://www.camara.cl/"
	const val DIPUTADOS_END_POINT = "diputados/diputados.aspx#mostrarDiputados"
	const val DIPUTADOS = "diputados/"
	const val GRID = "grid-2"
	const val H4 = "h4"
	const val HREF = "href"
	const val IMG = "img"
	const val SRC = "src"
	
	/*constantes senadores actuales*/
	const val URL_SENADORES_ACTUALES = "https://senado.cl/senado/site/edic/base/port/senadores.html"
	const val URL_SENADORES_ACTUALES_ROOT = "https://senado.cl/"
	const val HREF_WEB_PAGE_SENADORES = "href"
	const val CLASS_NAME_SENADORES_ACTUALES = "s0"
	const val NAME_SENADORES_ALT_LIST = "alt"
	const val IMAGES_LIST_SRC = "src"
	
	/*constantes partidos politicos*/
	const val URL_PARTIDOS_POLITICOS_2 =
		"https://servel.cl/partidos-constituidos/?mla_paginate_current=2"
	const val URL_PARTIDOS_POLITICOS_1 =
		"https://servel.cl/partidos-constituidos/?mla_paginate_current=1"
	const val TD_PARTIDOS_POLITICOS_CLASS = "td"
	const val TH_TITULO_PARTIDOS_POLITICOS_CLASS = "th-titulo"
	
	/*url alcaldes actuales*/
	const val URL_ALCALDES_ACTUALES = ""
	
	/*url consejales*/
	const val URL_CONSEJALES_ACTUALES = "https://www.cdch.cl/concejales-de-chile.aspx"
}
}