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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_CONTACT
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_MENU
import net.ezra.navigation.ROUTE_PROFILE
import net.ezra.ui.theme.AppTheme



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(navController: NavHostController) {
    Scaffold(

//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "MENU")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {navController.navigate(ROUTE_HOME) {
//                        popUpTo(ROUTE_MENU) { inclusive = true }
//                    }}) {
//                        Icon(Icons.Filled.ArrowBack, "backIcon")
//                    }
//                },
//                backgroundColor = MaterialTheme.colorScheme.background,
//                contentColor = Color.White,
//                elevation = 10.dp
//            )
//        },


        content = {
            var expanded by remember { mutableStateOf(false) }
            val suggestions = listOf("Web Development", "Branding", "App Development", "Graphic Design", "UI/UX")
            var selectedText by remember { mutableStateOf("") }

            var textfieldSize by remember { mutableStateOf(Size.Zero)}

            val icon = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown


            Column(Modifier.padding(20.dp)) {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = {Text("Menu")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
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
                navController.navigate(ROUTE_PROFILE) {
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

