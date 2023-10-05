package pe.pcs.desafioarquitectura

import com.google.gson.annotations.SerializedName
import pe.pcs.desafioarquitectura.ui.MovieResponse

data class MovieResult(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_pages") val total_pages: Int = 0,
    @SerializedName("total_results") val total_results: Int = 0,
    @SerializedName("results") val results: List<MovieResponse>
)
