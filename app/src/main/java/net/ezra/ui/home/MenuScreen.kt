package net.ezra.ui.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_CONTACT
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_MENU
import net.ezra.ui.theme.AppTheme



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(navController: NavHostController) {
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(text = "MENU")
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(ROUTE_HOME) {
                        popUpTo(ROUTE_MENU) { inclusive = true }
                    }}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },


        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xffd1cbca)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

        },

        bottomBar = {MottomBar(navController)}
    )
}

@Composable
fun MottomBar(navController: NavHostController) {
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


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    MenuScreen(rememberNavController())
}

