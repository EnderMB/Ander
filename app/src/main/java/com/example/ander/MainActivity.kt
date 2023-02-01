package com.example.ander

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ander.navigation.Go2
import com.example.ander.ui.theme.AnderTheme
import com.example.ander.views.giftD.GiftDetailScreen
import com.example.ander.views.giftL.GiftListScreen
import com.example.ander.views.viewmodel.GiftDetailViewModel
import com.example.ander.views.viewmodel.GiftListViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnderTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Go2.GiftList.route,
                ){
                    addGiftList(navController)

                    addGiftDetail()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
fun NavGraphBuilder.addGiftList(
    navController: NavController
){
    composable(
        route = Go2.GiftList.route
    ){
        val viewModel: GiftListViewModel = hiltViewModel()
        val state = viewModel.state.value
        val isRefreshing = viewModel.isRefreshing.collectAsState()

        GiftListScreen(
            state = state,
            navigateToBookDetail = { navController.navigate(Go2.GiftDetail.route) },
            isRefreshing = isRefreshing.value,
            refreshData = viewModel::getGiftList,
            onItemClick = {giftId ->
                navController.navigate(
                    Go2.GiftDetail.route + "?giftId=$giftId"
                )
            },
            deleteGift = viewModel::delGift
        )
    }
}

fun NavGraphBuilder.addGiftDetail(){
    composable(
        route= Go2.GiftDetail.route + "?giftId={giftID}"
    ){
        val viewModel: GiftDetailViewModel = hiltViewModel()
        val state = viewModel.state.value

        GiftDetailScreen(
            state = state,
            addNewGift = viewModel::addNewGift,
            updateGift = viewModel::updateGift
        )
    }
}

