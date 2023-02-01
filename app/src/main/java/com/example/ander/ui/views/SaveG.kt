package com.example.ander.ui.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SaveG(ViewModel: ViewModel){

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = ""
}