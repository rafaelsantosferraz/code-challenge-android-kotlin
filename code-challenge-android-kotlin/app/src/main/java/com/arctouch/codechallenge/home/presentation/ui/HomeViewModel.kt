package com.arctouch.codechallenge.home.presentation.ui

import com.arctouch.codechallenge.base.presentation.ui.BaseViewModel
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.domain.interactors.MoviesInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val moviesInteractor: MoviesInteractor
): BaseViewModel<HomeViewModel.State, HomeViewModel.Command>() {

    override fun initialState() = State()



    fun getUpcomingMovies(){
        newState(currentState().copy(isLoading = true))
        addJob(launch(exceptionHandler) {
            newState(currentState().copy(movies = moviesInteractor.getMoviesAsync().await(), isLoading = false))
        })
    }
    // endregion


    override fun onAllJobsComplete() {
        newState(currentState().copy(isLoading = false))
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        command.value = Command.Error(throwable)
    }

    data class State(
            val isLoading: Boolean? = null,
            val movies: List<Movie>? = null
    )

    sealed class Command {
        class Error(val throwable: Throwable): Command()
    }
}