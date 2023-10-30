package pe.pcs.desafioarquitectura.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pe.pcs.desafioarquitectura.data.local.MoviesDao
import pe.pcs.desafioarquitectura.data.local.toLocalMovie
import pe.pcs.desafioarquitectura.data.remote.RemoteDataSource


class MoviesRepository(
    private val dao: MoviesDao,
    private val remoteDataSource: RemoteDataSource
) {

    val movies: Flow<List<Movie>> = dao.getMovies().map { listMovie ->
        listMovie.map {
            it.toMovie()
        }
    }

    suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie.toLocalMovie())
    }

    suspend fun requestMovies() {
        val isDbEmpty = dao.countMovie() == 0

        if (isDbEmpty)
            dao.insertAll(remoteDataSource.getMovies().map { it.toLocalMovie() })
    }

}