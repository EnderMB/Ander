package com.example.ander.views.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ander.gifts.Gift
import com.example.ander.repositories.Result
import com.example.ander.repositories.GiftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import java.util.*

@HiltViewModel
class GiftDetailViewModel
@Inject
constructor(
    private val giftRepository: GiftRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state:MutableState<GiftDetailState> = mutableStateOf(GiftDetailState())
    val state: State<GiftDetailState>
        get() = _state

    init{
        Log.d("GiftDetailViewModel", "SavedStateHandle...")
        savedStateHandle.get<String>("giftId")?.let { giftId ->
            Log.d("GiftDetailViewModel", "GiftId: $giftId")
            getGift(giftId)
        }
    }

    fun addNewGift(nom:String, precio: String, desc: String, img:String){
        val gift = Gift(
            id = UUID.randomUUID().toString(),
            nom = nom,
            precio = precio,
            desc = desc,
            img = img
        )
        giftRepository.addNewGift(gift)
    }

    private fun getGift(giftId: String){
        giftRepository.getGiftbyId(giftId).onEach { result ->
            when(result){
                is Result.Error -> {
                    _state.value = GiftDetailState(error = result.message ?:"Unexpected error")
                }
                is Result.Loading -> {
                    _state.value = GiftDetailState(isLoading = true)
                }
                is Result.Sucess -> {
                    _state.value = GiftDetailState(gift = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateGift(newNom: String, newPrice: String){
        if(state.value.gift == null){
            _state.value = GiftDetailState(error = "Gift is null")
            return
        }
        val giftEdited = state.value.gift!!.copy(
            nom = newNom,
            precio = newPrice,
        )
        giftRepository.updateGift(giftEdited.id, giftEdited)
    }
}