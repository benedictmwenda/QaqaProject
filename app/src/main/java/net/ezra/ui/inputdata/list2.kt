package net.ezra.ui.inputdata

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import net.ezra.navigation.ROUTE_VIEW_PROD
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_HOME


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {

    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        fetchProduct(productId) { fetchedProduct ->
            product = fetchedProduct
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    // Display the product name if available
                    Text(
                        text = product?.fullname ?: "Details",
                        fontSize = 30.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_PROD)
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "backIcon",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffa96050),
                    titleContentColor = Color.White,
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xffb0a7c1)),
            ) {
                product?.let {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(it.imageUrl),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                        Text(text = it.fullname, style = MaterialTheme.typography.h5)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.email, style = MaterialTheme.typography.subtitle1)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.workexperience, style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    )

    Button(onClick = { navController.navigate(ROUTE_HOME) {
        popUpTo(ROUTE_HOME) { inclusive = true }
    } }) {
Text(text = "Home")
    }
}



suspend fun fetchProduct(productId: String): Product? {
    val db = FirebaseFirestore.getInstance()
    val productsCollection = db.collection("Product")

    return try {
        val documentSnapshot = productsCollection.document(productId).get().await()
        if (documentSnapshot.exists()) {
            val productData = documentSnapshot.data ?: return null
            Product(
                id = productId,
                fullname = productData["Fullname"] as String,
                email = productData["Email"] as String,
                workexperience = productData["Occupation"] as String,
                country = productData["Country"] as String,
                massage = productData["Description"] as String,
                // Add other product properties here
            )
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}