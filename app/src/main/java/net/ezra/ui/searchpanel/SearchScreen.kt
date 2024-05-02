package net.ezra.ui.searchpanel

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_CONTACT
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_SEARCH
import net.ezra.navigation.ROUTE_STUDENTLIST

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController) {
    Scaffold(

        content = {
            Button(
                onClick = {
                    navController.navigate(ROUTE_STUDENTLIST) {
                        popUpTo(ROUTE_SEARCH) { inclusive = true }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color(0xff5bb4fa)),
                border = BorderStroke(1.5.dp, Color.Transparent),
                contentPadding = PaddingValues(15.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text("Job INN", color = Color.Black)
            }
        },

        bottomBar = {BottomBar(navController)}
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") }, selected = (selectedIndex.value == 0), onClick = {
                navController.navigate(ROUTE_HOME) {
                    popUpTo(ROUTE_HOME) { inclusive = true }
                }
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Refresh,"")
        },
            label = { Text(text = "JobInn") }, selected = (selectedIndex.value == 1), onClick = {
                navController.navigate(ROUTE_ADD_STUDENTS) {
                    popUpTo(ROUTE_HOME) { inclusive = true }
                }
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.AccountCircle, "")
        },
            label = { Text(text = "Profile") }, selected = (selectedIndex.value == 2), onClick = {
                navController.navigate(ROUTE_CONTACT) {
                    popUpTo(ROUTE_HOME) { inclusive = true }
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    SearchScreen(navController = rememberNavController())
}

