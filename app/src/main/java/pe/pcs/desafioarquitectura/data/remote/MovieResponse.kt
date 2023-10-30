package pe.pcs.desafioarquitectura.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("original_title") val original_title: String,
    val favorite: Boolean = false
)
