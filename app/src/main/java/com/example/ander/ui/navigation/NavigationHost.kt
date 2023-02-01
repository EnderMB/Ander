package com.example.ander.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationHost(ViewModel: ViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Rutes.LoginScreen.rute){

    }
}
