package pe.pcs.desafioarquitectura.data.remote

import retrofit2.http.GET

interface MoviesService {

    @GET("discover/movie?api_key=83adb31856dbba8cf444a45ed3efa82a")
    suspend fun getMovies(): MovieResult

}