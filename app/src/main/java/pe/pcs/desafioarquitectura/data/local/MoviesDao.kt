package pe.pcs.desafioarquitectura.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("Select id, title, overview, posterPath, favorite From localmovie")
    fun getMovies(): Flow<List<LocalMovie>>

    @Insert
    suspend fun insertAll(movies: List<LocalMovie>)

    @Update
    suspend fun updateMovie(movie: LocalMovie)

    @Query("Select count(*) FROM localmovie")
    suspend fun countMovie(): Int
}