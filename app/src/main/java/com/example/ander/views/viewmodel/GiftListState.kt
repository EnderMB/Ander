package com.example.ander.views.viewmodel

import com.example.ander.gifts.Gift

data class GiftListState(
    //para crear una lista de regalos vacia
    val isLoading: Boolean = false,
    val gifts: List<Gift> = emptyList(),
    val error: String = ""
)
