package br.upf.deliveryapp.Activity.Users

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase


@Composable
fun LoginScreen(navController: NavHostController)  {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    var error by remember { mutableStateOf("") }
    var context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("LOGIN", style = MaterialTheme.typography.h2)
            Spacer(modifier = Modifier.height(8.dp))








            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())



            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                   val database = FirebaseDatabase.getInstance().reference.child("users")

                    database.get().addOnSuccessListener {data ->
                        var userFound = false
                        for(user in data.children){
                            val userEmail = user.child("email").getValue(String::class.java)
                            val userPassword = user.child("password").getValue(String::class.java)
                            val userType = user.child("userType").getValue(String::class.java)

                            if(email == userEmail && password == userPassword){
                                userFound = true
                                if(userType.toString().equals("admin")){
                                    navController.navigate("admin"){
                                        popUpTo(0)
                                    }
                                    break
                                }else{
                                    navController.navigate("main"){
                                }
                                    break
                            }
                        }else{
                            error="Email ou senha incorretos"


                            }                        }

                    }
                }){
                Text("Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                navController.navigate("signup"){popUpTo(0)}
            }) {
                Text("Não tem uma conta? Faça signup")
            }
            if(error.isNotEmpty()){
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}