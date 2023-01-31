package com.example.ander.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ander.R

@Composable
fun LoginScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        //Colocamos el parametro de align en esta parte para que afecte al hijo abajo
            Login(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Login(align: Modifier) {
    Column(modifier = Modifier) {
        HeaderImage()
    }
}

@Composable
fun HeaderImage() {
    Image(painter = painterResource(id = R.drawable.gifticon), contentDescription = "Header")
}
