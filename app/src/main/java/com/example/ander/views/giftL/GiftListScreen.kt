package com.example.ander.views.giftL

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ander.views.viewmodel.GiftListState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun GiftListScreen(
    state: GiftListState,
    navigateToBookDetail: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    onItemClick: (String) -> Unit,
    deleteGift: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToBookDetail,
                backgroundColor =Color.White,
                contentColor = Color(0xFF642EB4)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
            }
        }
    ) {
        GiftList(
            state = state,
            isRefreshing = isRefreshing,
            refreshData = refreshData,
            onItemClick = onItemClick,
            deleteGift = deleteGift
        )
    }
}