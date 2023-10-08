package pe.pcs.desafioarquitectura.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pe.pcs.desafioarquitectura.data.Movie

class LocalDataSource(private val dao: MoviesDao) {

    val movies: Flow<List<Movie>> = dao.getMovies().map { listMovie ->
        listMovie.map {
            it.toMovie()
        }
    }

    suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie.toLocalMovie())
    }

    suspend fun insertAll(movies: List<Movie>) {
        dao.insertAll(movies.map {
            it.toLocalMovie()
        })
    }

    suspend fun countMovie(): Int {
        return dao.countMovie()
    }

}