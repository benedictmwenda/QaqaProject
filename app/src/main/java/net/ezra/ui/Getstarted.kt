package net.ezra.ui


import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_GET
import net.ezra.navigation.ROUTE_HOME

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Getstarted(navController: NavHostController) {
    Box {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(2f, false),
            ) {
                Image(painter = painterResource(id = R.drawable.logoqs),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop
                )
                Text(text = "QAQA SOFTWARES",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 35.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        letterSpacing = 0.em,
                        background = Color.Transparent,
                        textDecoration = TextDecoration.None,
                    )
                )
            }

            Button(
                onClick = {
                    navController.navigate(ROUTE_HOME) {
                        popUpTo(ROUTE_GET) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("Get Started")
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewLight() {
    Getstarted(rememberNavController())
}

