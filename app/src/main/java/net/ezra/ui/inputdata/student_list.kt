package net.ezra.ui.inputdata


import android.annotation.SuppressLint
import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_CONTACT
import net.ezra.navigation.ROUTE_HOME
import net.ezra.ui.searchpanel.SearchScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StudentlistScreen(navController: NavHostController) {
    Scaffold(

        content = { Student_list(navController = navController, viewModel = FirestoreViewModel()) },

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

data class Item(

    val imageUrl: String? = "",
    val fullname: String? = "",
    val email: String? = "",
    val massage: String? = "",
    val workexperience: String? = "",
    val country: String? = "",

    )


class FirestoreViewModel : ViewModel() {

    private val firestore = Firebase.firestore
    private val itemsCollection = firestore.collection("Sign_up")

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        fetchItems()
    }

    fun fetchItems() {
        itemsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("FirestoreViewModel", "Error fetching items", error)
                return@addSnapshotListener
            }

            val itemList = mutableListOf<Item>()
            snapshot?.documents?.forEach { document ->
                val item = document.toObject(Item::class.java)?.copy(fullname = document.id)
                item?.let {
                    itemList.add(it)
                }
            }
            _items.value = itemList
        }
    }
}


@Composable
fun ItemList(items: List<Item>) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(10.dp)
        ) {

            items.forEach { item ->
                item {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(width = 300.dp, height = 250.dp)
                            .clickable { run { expanded = !expanded } }
                    ) {

                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.imageUrl)
                                .crossfade(true)
                                .build(),
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentDescription = item.fullname,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(10))
                        )

                        item.fullname?.let { Text(text = it) }
                        item.email?.let { Text(text = it) }
                        item.massage?.let { Text(text = it) }

                        if (expanded){
                            item.fullname?.let { Text(text = it) }
                            item.email?.let { Text(text = it) }
                            item.workexperience?.let { Text(text = it) }
                            item.country?.let { Text(text = it) }
                            item.massage?.let { Text(text = it) }
                        }

                    }

                }
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Student_list(navController: NavHostController, viewModel: FirestoreViewModel) {
    val items by viewModel.items.observeAsState(initial = emptyList())

    // Fetch items when the composable is first created
    LaunchedEffect(viewModel, key2 = true) {
        viewModel.fetchItems()
    }
    var expanded by remember { mutableStateOf(false) }
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(10.dp)
        ) {

            items.forEach { item ->
                item {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(width = 300.dp, height = 250.dp)
                            .clickable { run { expanded = !expanded } }
                    ) {

                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.imageUrl)
                                .crossfade(true)
                                .build(),
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentDescription = item.fullname,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(10))
                        )

                        item.fullname?.let { Text(text = it) }
                        item.email?.let { Text(text = it) }
                        item.massage?.let { Text(text = it) }

                        if (expanded){
                            item.fullname?.let { Text(text = it) }
                            item.email?.let { Text(text = it) }
                            item.workexperience?.let { Text(text = it) }
                            item.country?.let { Text(text = it) }
                            item.massage?.let { Text(text = it) }
                        }

                    }

                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    StudentlistScreen(navController = rememberNavController())
}