package pe.pcs.desafioarquitectura.ui.screens.core

sealed class UiState<out T> {
    object Loading: UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<T>(val message: String) : UiState<T>()
}

/*
sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    class Success<T>(val data: T) : UiState<T>()
    class Error<T>(val message: String) : UiState<T>()
}
 */