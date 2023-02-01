package com.example.ander.ui.navigation

sealed class Rutes (val rute: String) {
    object LoginScreen: Rutes("LoginScreen")
}