package net.ezra.ui.authentication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import net.ezra.navigation.ROUTE_LOGIN
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_PROFILE
import net.ezra.navigation.ROUTE_SEARCH


private var progressDialog: ProgressDialog? = null
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navController: NavHostController)  {
    val currentUser = FirebaseAuth.getInstance().currentUser
    var isLoading by remember { mutableStateOf(true) }
    var user: User? by remember { mutableStateOf(null) }
    var jobinnCount by remember { mutableIntStateOf(0) }
    val firestore = FirebaseFirestore.getInstance()
    val firestores = Firebase.firestore
    val context = LocalContext.current

    BackHandler {
        navController.popBackStack()

    }

    LaunchedEffect(key1 = currentUser?.uid) {
        if (currentUser != null) {
            val userDocRef = firestore.collection("users").document(currentUser.uid)
            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        user = document.toObject<User>()
                    }
                    isLoading = false
                }
                .addOnFailureListener { e ->
                    // Handle failure
                    isLoading = false
                }
        }
    }

    LaunchedEffect(Unit) {
        firestores.collection("Product")
            .get()
            .addOnSuccessListener { result ->
                jobinnCount = result.size()
            }
            .addOnFailureListener { exception ->
                // Handle failures
            }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Profile", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xff0FB06A),
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_PROFILE) { inclusive = true }
                        }}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon",tint = Color.White)
                    }
                },



                )
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = ScrollState(2))
                    .background(Color(0xff9AEDC9)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        OutlinedButton(onClick = {

                            navController.navigate(ROUTE_ADD_STUDENTS) {
                                popUpTo(ROUTE_PROFILE) { inclusive = true }
                            }

                        }) {

                            Text("Add Job Inn")

                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = "Total Job Inn: $jobinnCount")

                        IconButton(onClick = {
                            firestores.collection("Product")
                                .get()
                                .addOnSuccessListener { result ->
                                    jobinnCount = result.size()
                                }
                                .addOnFailureListener { exception ->
                                    // Handle failures
                                }
                        }) {
                            Icon(Icons.Filled.Refresh, "backIcon")
                        }
                    }





                }
            }
        }
    )
}