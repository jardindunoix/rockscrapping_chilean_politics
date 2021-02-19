package com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos

data class PartidoPoliticoEntity(val nombre : String = "" ,
                                 val paginaWeb : String = "" ,
                                 val mail : String = "" ,
                                 var picture : String = "")
