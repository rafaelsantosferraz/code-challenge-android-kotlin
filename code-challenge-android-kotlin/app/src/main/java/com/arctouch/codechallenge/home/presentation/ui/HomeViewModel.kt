package com.arctouch.codechallenge.home.presentation.ui

import com.arctouch.codechallenge.base.presentation.ui.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject


class HomeViewModel @Inject constructor(): BaseViewModel<HomeViewModel.State, HomeViewModel.Command>() {

    override fun initialState() = State()

    fun getUpcomingMovies(){

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
            val professionals: List<Any>? = null
    )

    sealed class Command {
        class Error(val throwable: Throwable): Command()
    }
}