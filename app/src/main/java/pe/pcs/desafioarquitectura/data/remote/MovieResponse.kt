package pe.pcs.desafioarquitectura.data.remote

import com.google.gson.annotations.SerializedName
import pe.pcs.desafioarquitectura.data.Movie
import pe.pcs.desafioarquitectura.data.local.LocalMovie

data class MovieResponse(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("original_title") val original_title: String,
    val favorite: Boolean = false
)

fun MovieResponse.toLocalMovie() = LocalMovie(
    id = 0,
    title = title,
    overview = overview,
    posterPath = poster_path,
    favorite = favorite
)

fun MovieResponse.toMovie() = Movie(
    id = 0,
    title = title,
    overview = overview,
    posterPath = poster_path,
    favorite = favorite
)