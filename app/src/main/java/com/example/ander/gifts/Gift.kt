package com.example.ander.gifts

data class Gift (
    val id: String,
    val nom: String,
    val precio: String,
    val desc: String,
    val img: String
        ){
    constructor() : this("","","","", "")
}
