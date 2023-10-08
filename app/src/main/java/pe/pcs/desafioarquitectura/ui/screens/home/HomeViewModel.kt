package pe.pcs.desafioarquitectura.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.pcs.desafioarquitectura.data.Movie
import pe.pcs.desafioarquitectura.data.MoviesRepository
import pe.pcs.desafioarquitectura.data.local.MoviesDao
import pe.pcs.desafioarquitectura.data.local.toLocalMovie
import pe.pcs.desafioarquitectura.data.local.toMovie
import pe.pcs.desafioarquitectura.data.remote.MoviesService
import pe.pcs.desafioarquitectura.data.remote.toLocalMovie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.requestMovies()

            repository.movies.collect {
                _state.value = UiState(movies = it)
            }
        }
    }

    fun onMovieClick(movie: Movie) {
        viewModelScope.launch {
            // Al hacer click actualizar la db
            // movie.copy(favorite = !movie.favorite ==> Has una copia de
            // esta pelicula, pero solo cambiale el valor de favorito
            repository.updateMovie(movie.copy(favorite = !movie.favorite))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

}