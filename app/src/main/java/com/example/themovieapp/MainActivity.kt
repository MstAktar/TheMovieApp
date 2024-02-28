package com.example.themovieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.themovieapp.model.Search
import com.example.themovieapp.ui.theme.TheMovieAppTheme
import com.example.themovieapp.viewmodel.MovieUIState
import com.example.themovieapp.viewmodel.MovieViewModel
import coil.compose.AsyncImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    MovieApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(movieViewModel: MovieViewModel = viewModel()){
   MovieScreen(uiState = movieViewModel.movieUIState)
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "The Movie App", color = MaterialTheme.colorScheme.primary)}
            )
        },
        content = {
            MovieScreen(uiState = movieViewModel.movieUIState)
        }
    )
}

@Composable
fun MovieScreen(uiState: MovieUIState){
    when(uiState){
        is MovieUIState.Loading -> LoadingScreen()
        is MovieUIState.Success -> MovieListScreen(movies = uiState.movies.Search)
        is MovieUIState.Error -> ErrorScreen()
    }
}

@Composable
fun MovieListScreen(movies: List<Search>){
    LazyColumn(
        modifier = Modifier
            .padding(top = 56.dp)
            .fillMaxSize()) {
        items(movies.size){ index ->
            val movieItem = movies[index]
            MovieCard(movie = movieItem)
        }
    }
}

@Composable
fun MovieCard(movie: Search){
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = movie.Poster,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = movie.Title ?: "No title found",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Year: ${movie.Year ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Type: ${movie.Type ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LoadingScreen(){
    Text(text = "Loading movies, please wait ")
}

@Composable
fun ErrorScreen(){
    Text(text = "Error loading the data from API", style = TextStyle(color = MaterialTheme.colorScheme.error))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheMovieAppTheme {
//        Greeting("Android")
    }
}