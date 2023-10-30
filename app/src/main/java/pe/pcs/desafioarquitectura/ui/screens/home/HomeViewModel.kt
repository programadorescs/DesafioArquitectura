package pe.pcs.desafioarquitectura.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.pcs.desafioarquitectura.data.Movie
import pe.pcs.desafioarquitectura.data.MoviesRepository
import pe.pcs.desafioarquitectura.ui.screens.core.UiState

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Movie>>> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repository.requestMovies()

            repository.movies.collect {
                _state.value = UiState.Success(it)
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

}