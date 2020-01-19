package com.arctouch.codechallenge.home.presentation.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.di.Injectable
import com.arctouch.codechallenge.base.presentation.ui.BaseViewModelFragment
import com.arctouch.codechallenge.home.domain.entities.Movie
import com.arctouch.codechallenge.home.presentation.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import kotlin.reflect.KClass





class HomeFragment : BaseViewModelFragment<HomeViewModel>(), Injectable {

    private val moviesRecyclerViewAdapter by lazy { HomeAdapter(listOf()) }
    private val moviesRecyclerViewLayoutManager by lazy { LinearLayoutManager(context) }




    //region Super ---------------------------------------------------------------------------------
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservables()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        viewModel.state.value?.movies.let {
            if(it.isNullOrEmpty()) {
                viewModel.getUpcomingMovies()
            }
            else {
                handleMovies(it)
                showLoadingView(false)
            }
        }
    }

    override fun onDestroyView() {
        home_fragment_recyclerview.apply{
            adapter = null
            layoutManager = null
        }
        super.onDestroyView()
    }

    // BaseViewModelFragment
    override fun getViewModel(): KClass<HomeViewModel> = HomeViewModel::class
    //endregion




    //region Setup ---------------------------------------------------------------------------------
    private fun setupRecyclerView(){
        home_fragment_recyclerview.apply{
            layoutManager = moviesRecyclerViewLayoutManager
            adapter = moviesRecyclerViewAdapter
        }
    }

    private fun setupObservables() {
        viewModel.state.observe(this, Observer {
            render(it)
        })

        viewModel.command.observe(this, Observer {
            handleCommand(it)
        })
    }
    // endregion



    //region Private -------------------------------------------------------------------------------
    private fun showLoadingView(isLoading: Boolean){
        if(isLoading){
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }
    //endregion




    // region State --------------------------------------------------------------------------------
    private fun render(state: HomeViewModel.State?) {
        viewModel.apply {
            state?.run {
                part("isLoading", state.isLoading) {
                    if (it != null) {
                        showLoadingView(it)
                    }
                }

                part("movies", state.movies) {
                    if (it != null) {
                        handleMovies(it)
                    }
                }
            }
        }
    }

    private fun handleMovies(movies: List<Movie>){
        moviesRecyclerViewAdapter.movies = movies
        moviesRecyclerViewAdapter.notifyDataSetChanged()
    }
    // endregion




    // region Command ------------------------------------------------------------------------------
    private fun handleCommand(command: HomeViewModel.Command?) {
        command.let {
            when (command) {
                is HomeViewModel.Command.Error -> handleError(command.throwable)
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        when(throwable) {
            is ConnectException -> showDialog(R.string.error_no_internet)
            is SocketTimeoutException -> showDialog(R.string.error_bad_connection)
            is TimeoutCancellationException -> showDialog(R.string.error_bad_connection)
            is HttpException ->
                when(throwable.code()){
                    //HttpURLConnection.HTTP_UNAUTHORIZED -> logout()
                    HttpURLConnection.HTTP_PRECON_FAILED -> showDialog(R.string.error_precon_failed)
                    else -> showDialog(R.string.error_unknown)
                }
            else -> showDialog(R.string.error_unknown)
        }
        Log.e(TAG, "exception: ", throwable)
    }
    // endregion
}
