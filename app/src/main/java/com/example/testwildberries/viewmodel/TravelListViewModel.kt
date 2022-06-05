package com.example.testwildberries.viewmodel

import androidx.lifecycle.*
import com.example.testwildberries.model.FeedModelState
import com.example.testwildberries.model.Travel
import com.example.testwildberries.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TravelListViewModel @Inject constructor(private val repository: TravelRepository) :
    ViewModel() {

    private var _state = MutableLiveData(FeedModelState())
    val state: LiveData<FeedModelState> = _state

    private var _tours = MutableLiveData<List<Travel>>()
    val tours: LiveData<List<Travel>> = _tours

    private val listTours = mutableListOf<Travel>()

    fun liked(travel: Travel) {
        travel.liked = !travel.liked
    }

    init {
        showTours()
    }

    fun showTours() {
        viewModelScope.launch {
            _state.value = FeedModelState(loading = true)
            delay(1_000)
            try {
                val tours = repository.getFromApi()
                for (tour in tours) {
                    listTours.add(tour)
                }
                _tours.value = listTours
            } catch (e: IOException) {
                _state.value = FeedModelState(error = true)
            } finally {
                _state.value = FeedModelState(loading = false)
            }
        }
    }
}