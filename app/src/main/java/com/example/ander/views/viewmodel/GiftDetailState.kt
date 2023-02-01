package com.example.ander.views.viewmodel

import com.example.ander.gifts.Gift

data class GiftDetailState(
    val isLoading: Boolean = false,
    val gift: Gift? = null,
    val error: String = ""
)