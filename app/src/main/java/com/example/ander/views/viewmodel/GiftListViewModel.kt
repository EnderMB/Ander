package com.example.ander.views.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.ander.repositories.Result
import com.example.ander.repositories.GiftRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GiftListViewModel
    @Inject
    constructor(
    private val giftRepository: GiftRepository
) : ViewModel() {
    private val _state: MutableState<GiftListState> = mutableStateOf(GiftListState())
    val state: State<GiftListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getGiftList()
    }

    fun getGiftList() {
        giftRepository.getGiftList().onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value = GiftListState(error = result.message ?: "Unexpected Error")
                }
                is Result.Loading -> {
                    _state.value = GiftListState(isLoading = true)
                }
                is Result.Sucess -> {
                    _state.value = GiftListState(gifts = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun delGift(giftId: String) {
        giftRepository.delGift(giftId)
    }
}