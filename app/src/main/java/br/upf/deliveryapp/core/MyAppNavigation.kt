package br.upf.deliveryapp.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.upf.deliveryapp.ui.screens.HomeScreen
import br.upf.deliveryapp.ui.screens.LoginPage
import br.upf.deliveryapp.ui.screens.SignupPage
import br.upf.deliveryapp.viewmodel.AuthViewModel


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup"){
            SignupPage(modifier,navController,authViewModel)
        }
        composable("home"){
            HomeScreen(navController)
        }
    })
}

