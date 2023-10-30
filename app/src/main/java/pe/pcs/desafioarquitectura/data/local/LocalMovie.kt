package pe.pcs.desafioarquitectura.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import pe.pcs.desafioarquitectura.data.Movie

@Entity(tableName = "localmovie")
data class LocalMovie(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean
)

fun Movie.toLocalMovie() = LocalMovie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    favorite = favorite
)