package pe.pcs.desafioarquitectura.data

import pe.pcs.desafioarquitectura.data.local.LocalMovie
import pe.pcs.desafioarquitectura.data.remote.MovieResponse

data class Movie(
    var id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean
)
fun LocalMovie.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    favorite = favorite
)

fun MovieResponse.toMovie() = Movie(
    id = 0,
    title = title,
    overview = overview,
    posterPath = poster_path,
    favorite = favorite
)