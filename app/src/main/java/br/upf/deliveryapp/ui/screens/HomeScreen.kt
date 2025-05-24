package br.upf.deliveryapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.upf.deliveryapp.core.MyBottomBar
import br.upf.deliveryapp.core.TopBar

@Composable
fun HomeScreen(navController: NavController) {



    Scaffold(
        bottomBar = { MyBottomBar() },

    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ){
            item{
                TopBar()
            }
        }
    }


}
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {

}