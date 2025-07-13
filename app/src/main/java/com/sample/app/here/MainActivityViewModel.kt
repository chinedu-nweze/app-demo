package com.sample.app.here

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sample.app.here.model.Data
import com.sample.app.here.model.People
import com.sample.app.here.model.ViewState
import com.sample.app.here.service.ServiceDriver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val TAG = MainActivityViewModel::class.java.name
    private val call = ServiceDriver.service.getPeople()
    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState

    /**
     * Fetch a List of People from the API
     */
    fun getPeople() {
        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    Log.d(TAG, "List of People ${post?.people?.size}")
                    post?.people?.let { listOfPeople ->
                        updateViewState(listOfPeople)
                    } ?: updateViewState(emptyList())

                } else {
                    Log.e(TAG, "API Call to get List of People Failed")
                    updateViewState(emptyList())
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                // Handle failure
                Log.d(TAG, "Error occurred: $t")
                updateViewState(emptyList())
            }
        })
    }

    private fun updateViewState(people: List<People>) {
        _viewState.update { state ->
            state.copy(
                people = people
            )
        }
    }
}