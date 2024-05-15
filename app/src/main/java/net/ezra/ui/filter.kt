package net.ezra.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


// ViewModel to handle data retrieval
class MyViewModel : ViewModel() {
//    private val firestore = Firebase.firestore
//    private val collection = firestore.collection("Product")
//
//    val filteredData: LiveData<List<YourDataModel>> = liveData {
//        val querySnapshot = collection
//            .whereEqualTo("First Name", "Occupation")
//            .get()
//            .await()
//
//        val dataList = mutableListOf<YourDataModel>()
//        for (document in querySnapshot.documents) {
//            val data = document.toObject(YourDataModel::class.java)
//            data?.let { dataList.add(it) }
//        }
//        emit(dataList)
//    }
}

// Composable function to display filtered data
//@Composable
//fun DisplayFilteredData(viewModel: MyViewModel = viewModel()) {
//    val filteredData by viewModel.filteredData.observeAsState(emptyList())
//
//    LazyColumn {
//        items(filteredData) { item ->
//            Text(text = item.someField)
//            // Display other fields of your data model as needed
//        }
//    }
//}
