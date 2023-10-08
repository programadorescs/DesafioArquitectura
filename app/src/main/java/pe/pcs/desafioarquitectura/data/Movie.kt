package pe.pcs.desafioarquitectura.data

import pe.pcs.desafioarquitectura.data.local.LocalMovie

data class Movie(
    var id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean
)
