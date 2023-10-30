package pe.pcs.desafioarquitectura.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import pe.pcs.desafioarquitectura.data.MoviesRepository
import pe.pcs.desafioarquitectura.data.local.MoviesDatabase
import pe.pcs.desafioarquitectura.data.remote.RemoteDataSource
import pe.pcs.desafioarquitectura.ui.screens.home.Home

class MainActivity : ComponentActivity() {

    private lateinit var db: MoviesDatabase

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crea la DB
        db = Room.databaseBuilder(
            applicationContext,
            MoviesDatabase::class.java,
            "movies-db"
        ).build()

        val repository = MoviesRepository(
            dao = db.moviesDao(),
            remoteDataSource = RemoteDataSource()
        )

        setContent {
            Home(repository)
        }
    }
}
