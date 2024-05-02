package net.ezra.ui.inputdata


import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import net.ezra.R
import java.util.UUID


@Composable
fun AddStudents(navController: NavHostController) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.black_background),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
                Column(

                    modifier = Modifier
                        .fillMaxSize()
//
                        .padding(5.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Sign up to find work you love",style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontStyle = FontStyle.Normal,
                        letterSpacing = 0.1.em,
                        background = Color.Transparent,
                        textDecoration = TextDecoration.None
                    )
                    )

                    var photoUri: Uri? by remember { mutableStateOf(null) }

                    val launcher =
                        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                            photoUri = uri
                        }


                    var fullname by rememberSaveable {
                        mutableStateOf("")
                    }

                    var email by rememberSaveable {
                        mutableStateOf("")
                    }

                    OutlinedTextField(
                        value = fullname,
                        onValueChange = { fullname = it },
                        label = { Text(text = "Full Name", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "@.gmail.com", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )


                    var phone by rememberSaveable {
                        mutableStateOf("")
                    }

                    var workexperience by rememberSaveable {
                        mutableStateOf("")
                    }

                    var country by rememberSaveable {
                        mutableStateOf("")
                    }

                    var massage by rememberSaveable {
                        mutableStateOf("")
                    }





                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text(text = "Phone Number", color = Color.White) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(

                        value = workexperience,
                        onValueChange = { workexperience = it },
                        label = { Text(text = "Occupation", color = Color.White) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = country,
                        onValueChange = { country = it },
                        label = { Text(text = "Country", color = Color.White) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = massage,
                        onValueChange = { massage = it },
                        label = { Text(text = "Massage Description", color = Color.White) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()

                    )


                    OutlinedButton(
                        onClick = {
                            launcher.launch(
                                PickVisualMediaRequest(
                                    //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                                    //Or use .VideoOnly if you only want videos.
                                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ) {
                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = "",)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Add a profile picture", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (photoUri != null) {
                        //Use Coil to display the selected image
                        val painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(data = photoUri)
                                .build()
                        )

                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .padding(5.dp)
                                .size(150.dp)
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray),
                            contentScale = ContentScale.Crop,

                            )
                    }

                    Button(
                        modifier = Modifier
//                .background(color = Color(0xff0606FF))
                        , onClick = {
                            photoUri?.let {
                                uploadImageToFirebaseStorage(
                                    it,
                                    fullname,
                                    phone,
                                    email,
                                    workexperience,
                                    country,
                                    massage
                                )
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Register")
                    }


                }



            }
        }
    }
}




fun uploadImageToFirebaseStorage(
    imageUri: Uri,
    fullname: String,
    phone: String,
    email: String,
    workexperience: String,
    country: String,
    massage: String
) {
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}")

    val uploadTask = imageRef.putFile(imageUri)
    uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        imageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            saveToFirestore(
                downloadUri.toString(),
                fullname,
                phone,
                email,
                workexperience,
                country,
                massage
            )
        } else {


        }
    }
}

fun saveToFirestore(
    imageUrl: String,
    fullname: String,
    phone: String,
    email: String,
    workexperience: String,
    country: String,
    massage: String
) {
    val db = Firebase.firestore
    val imageInfo = hashMapOf(
        "imageUrl" to imageUrl,
        "First Name" to fullname,
        "Last Name" to phone,
        "Email" to email,
        "Occupation" to workexperience,
        "Country" to country,
        "Massage" to massage


        )




    db.collection("Sign_up")
        .add(imageInfo)
        .addOnSuccessListener {
            "Successfully sent"
        }
        .addOnFailureListener {
           " Handle  the error and try Again in a few minutes"
        }
}
@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    AddStudents(rememberNavController())
}




