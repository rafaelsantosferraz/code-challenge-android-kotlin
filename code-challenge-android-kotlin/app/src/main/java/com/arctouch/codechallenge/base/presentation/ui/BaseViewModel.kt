package com.arctouch.codechallenge.base.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S, C> : ViewModel(), CoroutineScope {

    private var jobs = mutableListOf<Job>()
    private val updated = HashMap<String, Any?>()


    var simultaneousJobs = 0
        set(value){
            if(value == 0){
                onAllJobsComplete()
            }
            field = value
        }


    val command = SingleLiveEvent<C>()
    val state = MutableLiveData<S>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main



    init {
        newState(initialState())
    }

    abstract fun initialState(): S


    abstract fun onAllJobsComplete()



    //region Public ----------------------------------------------------------------------------------------------------
    fun <T> part(name: String, newValue: T, updater: (T) -> Unit) {
        if (!updated.containsKey(name) || updated[name] != newValue) {
            updater(newValue)
            updated[name] = newValue
        }
    }

    protected fun newState(state: S) {
        this.state.value = state
    }

    fun currentState(): S {
        return state.value!!
    }

    fun addJob(job: Job, isToBeCount: Boolean = true) {
        if (isToBeCount) {
            simultaneousJobs++
            job.invokeOnCompletion { simultaneousJobs-- }
        }
        jobs.add(job)
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
        simultaneousJobs = 0
    }

    interface BaseCommand{


    }
    //endregion
}

