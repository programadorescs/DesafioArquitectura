package pe.pcs.desafioarquitectura.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import pe.pcs.desafioarquitectura.data.Movie
import pe.pcs.desafioarquitectura.data.MoviesRepository
import pe.pcs.desafioarquitectura.ui.screens.core.UiState
import pe.pcs.desafioarquitectura.ui.theme.DesafioArquitecturaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(moviesRepository: MoviesRepository) {

    DesafioArquitecturaTheme {

        val viewModel: HomeViewModel = viewModel { HomeViewModel(moviesRepository) }
        val state by viewModel.state.collectAsState()

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Scaffold(topBar = { TopAppBar(title = { Text(text = "MOVIES") }) }) { padding ->
                state.let {
                    when (it) {
                        is UiState.Error -> Toast.makeText(
                            LocalContext.current,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()

                        is UiState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is UiState.Success -> {
                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(120.dp),
                                modifier = Modifier.padding(padding),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                contentPadding = PaddingValues(4.dp)
                            ) {
                                items(it.data) { theMovie ->
                                    MovieItem(
                                        movie = theMovie,
                                        onClick = { viewModel.onMovieClick(theMovie) }
                                    )
                                }
                            }
                        }
                    }
                }

            }

        }
    }

}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(2 / 3f)
            )

            if (movie.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = Color.Red
                )
            }
        }

        Text(
            text = movie.title,
            modifier = Modifier
                .padding(16.dp)
                .height(48.dp),
            maxLines = 2
        )
    }
}