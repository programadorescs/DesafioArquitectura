package pe.pcs.desafioarquitectura.data

import kotlinx.coroutines.flow.Flow
import pe.pcs.desafioarquitectura.data.local.LocalDataSource
import pe.pcs.desafioarquitectura.data.remote.RemoteDataSource


class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    val movies: Flow<List<Movie>> = localDataSource.movies

    suspend fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }

    suspend fun requestMovies() {
        val isDbEmpty = localDataSource.countMovie() == 0

        if (isDbEmpty)
            localDataSource.insertAll(remoteDataSource.getMovies())
    }

}