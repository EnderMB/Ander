package com.example.ander.ui.gifts

data class Gift (
    val id: String,
    val nom: String,
    val precio: Float,
    val desc: String
        ){
    constructor() : this("","",0.0f,"")
}
