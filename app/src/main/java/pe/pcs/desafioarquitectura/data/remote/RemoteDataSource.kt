package pe.pcs.desafioarquitectura.data.remote

import pe.pcs.desafioarquitectura.data.Movie
import pe.pcs.desafioarquitectura.data.toMovie

class RemoteDataSource {
    suspend fun getMovies(): List<Movie> {
        return Network.provideRetrofit().create(MoviesService::class.java)
            .getMovies()
            .results.map {
                it.toMovie()
            }
    }
}