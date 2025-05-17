package br.upf.deliveryapp.core


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.upf.deliveryapp.R

@Composable
@Preview
fun MyBottomBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(modifier = Modifier.background(color = Color.Gray)
    ){
        bottomMenuItemsList.forEach{ bottomMenuItem ->
            NavigationBarItem(
                selected = (selectedItem == bottomMenuItem.label),
                onClick = {
                    selectedItem = bottomMenuItem.label
                    if(bottomMenuItem.label == "Cart"){
                        //context.startActivity(Intent(context,CartActivity::class.java))

                    }else{
                        Toast.makeText(context,bottomMenuItem.label,Toast.LENGTH_SHORT).show()
                    }
                },
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 8.dp).size(20.dp)
                    )
                }
            )
        }


    }
}



    data class BottomMenuItem(val label: String, val icon: Painter)

    @Composable
    fun prepareBottomMenu(): List<BottomMenuItem>{
        val bottomMenuItemsList = mutableListOf<BottomMenuItem>()

        bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = painterResource(id = R.drawable.home_24px)))
        bottomMenuItemsList.add(BottomMenuItem(label = "Cart", icon = painterResource(id = R.drawable.shopping_cart_24px)))
        bottomMenuItemsList.add(BottomMenuItem(label = "Order", icon = painterResource(id = R.drawable.list_alt_24px)))
        bottomMenuItemsList.add(BottomMenuItem(label = "Track", icon = painterResource(id = R.drawable.location_on_24px)))

        return bottomMenuItemsList
    }