package com.example.ander.gifts
data class User (
    val id: String,
    val nom: String,
    val pass: String,
    val date: String
    ){
     constructor(): this("", "", "", "")

}