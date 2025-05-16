package br.upf.deliveryapp.data

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import br.upf.deliveryapp.R

@Composable
@Preview
fun MyBottomBar() {
    val bottomMenuList = prepareBottomMenu()
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar {
        backgroundColor = colorResource(R.color.grey),

    }
}