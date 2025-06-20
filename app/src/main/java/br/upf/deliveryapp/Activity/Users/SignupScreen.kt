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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase


@Composable
fun SignupScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }
    var context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("SIGNUP", style = MaterialTheme.typography.h2)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = userType,
                onValueChange = { userType = it },
                label = { Text("User Type") })

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty() && phone.isNotEmpty()) {
                        val database = FirebaseDatabase.getInstance().reference.child("users")
                        val userId = database.push().key ?: ""

                        val userData = mapOf(
                            "name" to name,
                            "phone" to phone,
                            "email" to email,
                            "password" to password,
                            "userType" to userType
                        )
                        database.child(userId).setValue(userData).addOnSuccessListener {
                            navController.navigate("login") {
                                popUpTo(0)
                            }

                        }
                    }else{
                        error="Preencha todos os campos"

                    }
                }){
                Text("Criar conta")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                navController.navigate("login"){popUpTo(0)}
            }) {
                Text("Já tem uma conta? Faça login")
            }
                if(error.isNotEmpty()){
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }