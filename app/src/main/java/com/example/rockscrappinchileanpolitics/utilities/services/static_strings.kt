package com.example.rockscrappinchileanpolitics.utilities.services

class StaticStrigns {
    companion object {

        /*constantes senadores actuales*/
        const val URL_SENADORES_ACTUALES =
            "https://senado.cl/senado/site/edic/base/port/senadores.html"
        const val URL_SENADORES_ACTUALES_ROOT = "https://senado.cl/"
        const val HREF_WEB_PAGE_SENADORES = "href"
        const val CLASS_NAME_SENADORES_ACTUALES = "s0"
        const val NAME_SENADORES_ALT_LIST = "alt"
        const val IMAGES_LIST_SRC = "src"

        /*constantes diputados actuales*/
        const val URL_DIPUTADOS_ACTUALES_ROOT = "https://www.camara.cl/"
        const val DIPUTADOS_END_POINT = "diputados/diputados.aspx#mostrarDiputados"
        const val DIPUTADOS = "diputados/"
        const val GRID = "grid-2"
        const val H4 = "h4"
        const val HREF = "href"
        const val IMG = "img"
        const val SRC = "src"

        /*constantes partidos politicos*/
        const val URL_PARTIDOS_POLITICOS_2 =
            "https://servel.cl/partidos-constituidos/?mla_paginate_current=2"
        const val URL_PARTIDOS_POLITICOS_1 =
            "https://servel.cl/partidos-constituidos/?mla_paginate_current=1"
        const val TD_PARTIDOS_POLITICOS_CLASS = "td"
        const val TH_TITULO_PARTIDOS_POLITICOS_CLASS = "th-titulo"

        /*xconstantes alcaldes actuales*/
        const val URL_ALCALDES_ACTUALES = ""

        /*constantes consejales*/
        const val URL_CONSEJALES_ACTUALES = "https://www.cdch.cl/concejales-de-chile.aspx"

        /*comunas consejales actuales*/
        const val URL_COMUNA_DETALLE = "https://www.cdch.cl/Comuna/"

        /*Database name*/
        const val DATABASE_NAME ="politics_database"
        /*table names*/
        const val COMUNAS_CONSEJALES_TABLE = "comunas_consejales_table"
        const val CONSEJALES_ACTUALES_DETALLE_TABLE = "consejales_actuales_detalle_table"
        const val CONSEJALES_CANDIDATO_DETALLE_TABLE = "consejales_candidato_detalle_table"
        const val DIPUTADOS_ACTUALES_TABLE = "diputados_actuales_table"
        const val DIPUTADOS_CANDIDATOS_TABLE = "diputados_candidatos_table"
        const val SENADORES_ACTUALES_TABLE = "senadores_actuales_table"
        const val SENADORES_CANDIDATOS_TABLE = "senadores_candidatos_table"
        const val PARTIDOS_POLITICOS_TABLE = "partidos_politicos_table"
    }
}