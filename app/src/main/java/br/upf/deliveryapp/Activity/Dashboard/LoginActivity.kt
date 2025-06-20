package br.upf.deliveryapp.Activity.Dashboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.upf.deliveryapp.Activity.BaseActivity
import br.upf.deliveryapp.Activity.Users.LoginScreen

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen(navController = rememberNavController())
        }
    }
}