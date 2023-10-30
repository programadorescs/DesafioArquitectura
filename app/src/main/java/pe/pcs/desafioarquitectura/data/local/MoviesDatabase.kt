package pe.pcs.desafioarquitectura.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMovie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}