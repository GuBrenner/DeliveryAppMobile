package br.upf.deliveryapp.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.upf.deliveryapp.ui.screens.CadastroScreen
import br.upf.deliveryapp.ui.screens.HomeScreen
import br.upf.deliveryapp.ui.screens.LoginScreen

@Composable
fun Routes(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ){
        composable("LoginScreen") {
            LoginScreen(navController)
        }
        composable("CadastroScreen") {
            CadastroScreen(navController)

        }
        composable("HomeScreen"){
            HomeScreen(navController)
        }

    }
}

