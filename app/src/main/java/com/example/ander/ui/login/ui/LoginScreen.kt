package com.example.ander.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ander.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        //Colocamos el parametro de align en esta parte para que afecte al hijo abajo
            Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(align: Modifier, viewModel: LoginViewModel) {

    val email : String by viewModel.email.observeAsState(initial = "")
    val pass : String by viewModel.pass.observeAsState(initial = "")
    val loginEnable:Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading:Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else{
        Column(modifier = Modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.padding(16.dp))

            MailField(email) {viewModel.onLoginChanged(it, pass)}

            Spacer(modifier = Modifier.padding(16.dp))

            PassField(pass) {viewModel.onLoginChanged(email, it)}

            Spacer(modifier = Modifier.padding(8.dp))

            ForgotPass(Modifier.align(Alignment.End))

            Spacer(modifier = Modifier.padding(16.dp))

            LoginButton(loginEnable){
                coroutineScope.launch {
                    viewModel.onLoginSelected() }
               }
        }
    }
}

@Composable
//Controlamos el estado llamando a la funcion de loginSelected (Principio singleSourceOfTrue)
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = {onLoginSelected},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFA071E6),
            disabledBackgroundColor = Color(0xFFC9AFF0),
            contentColor = Color(0xFF32175A),
            disabledContentColor = Color(0xFF642EB4)
        ), enabled = loginEnable
    ) {
        Text(text = "Sign in")
    }
}

@Composable
fun ForgotPass(modifier: Modifier) {
    Text(text = "Forgot your Pass?",
        modifier = modifier.clickable{},
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF642EB4)
    )
}

@Composable
fun PassField(pass: String, onTextFieldChanged: (String) -> Unit) {
    TextField(value =pass, onValueChange = {onTextFieldChanged(it)},
    placeholder = { Text(text = "Password")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF32175A),
            backgroundColor = Color(0xFFDEDDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
fun MailField(email: String, onTextFieldChanged:(String) -> Unit) {

    TextField(value = email, onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text ="Mail") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        //para que no crezca el componenete al clicarlo
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF32175A),
            backgroundColor = Color(0xFFDEDDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.gifticon),
        contentDescription = "Header",
    modifier = modifier
    )
}
