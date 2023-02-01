package com.example.ander.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import com.example.ander.ui.login.ui.LoginScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutes.LoginScreen.rute){
        composable(Rutes.LoginScreen.rute){ LoginScreen(navController)}
    }
}