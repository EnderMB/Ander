package com.example.ander.views.giftL

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ander.views.giftL.comm.GiftListItem
import com.example.ander.views.viewmodel.GiftListState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterialApi
@Composable
fun GiftList(
    state: GiftListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    onItemClick: (String) -> Unit,
    deleteGift: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        //para que se actualice al darle para abajo
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = refreshData
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                //para añadir items
                items(
                    items = state.gifts,
                    key = { gift ->
                        gift.id
                    }
                ) { gift ->
                    var isDel by remember { mutableStateOf(false) }
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            Log.d("GiftList", "Dismiss value: ${it.name}")
                            if (it == DismissedToEnd) isDel = !isDel
                            it != DismissedToEnd
                        }
                    )

                    //para cancelar
                    SwipeToDismiss(state = dismissState,
                        directions = setOf(StartToEnd),
                        dismissThresholds = {
                            FractionalThreshold(0.8f)
                        },
                        background = {
                            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    Default -> Color(0xFF642EB4)
                                    DismissedToEnd -> Color.DarkGray
                                    DismissedToStart -> Color.DarkGray
                                }
                            )
                            val alignment = when (direction) {
                                StartToEnd -> Alignment.CenterStart
                                EndToStart -> Alignment.CenterEnd
                            }

                            val icon = when (direction) {
                                StartToEnd -> Icons.Default.Delete
                                EndToStart -> Icons.Default.Delete
                            }
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == Default) 1f else 1.16f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        }
                    ) {
                        if (isDel) {
                            deleteGift(gift.id)
                        } else {
                            GiftListItem(
                                gift,
                                onItemClick = onItemClick
                            )
                        }
                    }
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
