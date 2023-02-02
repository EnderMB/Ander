package com.example.ander.views.giftD

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ander.views.viewmodel.GiftDetailState
import kotlin.reflect.KFunction4

@Composable
fun GiftDetailScreen(
    state: GiftDetailState,
    addNewGift: KFunction4<String, String, String, String, Unit>,
    updateGift: (String, String) -> Unit,
) {
    var nom by remember(state.gift?.nom) { mutableStateOf(state.gift?.nom ?: "") }
    var precio by remember(state.gift?.precio) { mutableStateOf(state.gift?.precio ?: "") }
    var desc by remember(state.gift?.desc) { mutableStateOf(state.gift?.desc ?: "") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
                value = nom,
                onValueChange = { nom = it },
                label = {
                    Text(text = "Name")
                }
            )

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
                value = nom,
                onValueChange = { nom = it },
                label = {
                    Text(text = "Price")
                }
            )

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
                value = desc,
                onValueChange = { desc = it },
                label = {
                    Text(text = "Description")
                }
            )

            if (state.error.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    text = state.error,
                    style = TextStyle(
                        color = Color(0xFF8A0B0B),
                        textAlign = TextAlign.Center
                    )
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        /*.align(Alignment.Center)*/)
            } else {
                if (state.gift?.id != null) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            /*.align(Alignment.BottomCenter)*/,
                        onClick = {
                            updateGift(nom, precio)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF9A6EDA)
                        )
                    ) {
                        Text(
                            text = "Update Gift",
                            color = Color(0xFF642EB4)
                        )
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            /*.align(Alignment.BottomCenter)*/,
                        onClick = {
                            addNewGift(nom, precio, desc)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF9A6EDA)
                        )
                    ) {
                        Text(
                            text = "Add New Gift",
                            color = Color(0xFF642EB4)
                        )
                    }
                }
            }
        }
    }
}