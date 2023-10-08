package pe.pcs.desafioarquitectura.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_pages") val total_pages: Int = 0,
    @SerializedName("total_results") val total_results: Int = 0,
    @SerializedName("results") val results: List<MovieResponse>
)
