package com.example.ander.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

sealed class Go2(

    val route: String,
    val arguments: List<NamedNavArgument>
) {
    object GiftList : Go2("giftList", emptyList())
    object GiftDetail : Go2(
        route = "giftDetail",
        arguments = listOf(
            navArgument("giftId") {
                nullable = true
            }
        )
    )
}