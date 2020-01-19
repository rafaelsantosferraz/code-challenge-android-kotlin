package com.arctouch.codechallenge.home.presentation.ui.list

import com.arctouch.codechallenge.base.presentation.ui.BaseViewModel
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.domain.interactors.MoviesInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val moviesInteractor: MoviesInteractor
): BaseViewModel<HomeViewModel.State, HomeViewModel.Command>() {

    override fun initialState() = State()


    //region Public
    fun getUpcomingMovies(){
        newState(currentState().copy(isLoading = true))
        addJob(launch(exceptionHandler) {
            val previousLoadMovies = state.value?.movies ?: mutableListOf()
            val movies = moviesInteractor.getMoviesAsync(previousLoadMovies).await()
            newState(currentState().copy(movies = movies.toMutableList(), isLoading = false, isLastPage = moviesInteractor.isLastPage))
        })
    }

    fun onMovieClick(movie: Movie){
        command.value = Command.NavigateToMoviePage(movie)
    }
    // endregion



    override fun onAllJobsComplete() {
        newState(currentState().copy(isLoading = false))
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        command.value = Command.Error(throwable)
    }

    data class State(
            val isLoading: Boolean? = false,
            val isLastPage: Boolean? = false,
            val movies: MutableList<Movie>? = null
    )

    sealed class Command {
        class Error(val throwable: Throwable): Command()
        class NavigateToMoviePage(val movie: Movie): Command()
    }
}