package net.ezra.ui.Jobs

////import android.annotation.SuppressLint
////import android.util.Log
////import androidx.compose.foundation.BorderStroke
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.ScrollState
////import androidx.compose.foundation.background
////import androidx.compose.foundation.border
////import androidx.compose.foundation.horizontalScroll
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.Box
////import androidx.compose.foundation.layout.Column
////import androidx.compose.foundation.layout.PaddingValues
////import androidx.compose.foundation.layout.Row
////import androidx.compose.foundation.layout.Spacer
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.foundation.layout.height
////import androidx.compose.foundation.layout.padding
////import androidx.compose.foundation.layout.size
////import androidx.compose.foundation.layout.width
////import androidx.compose.foundation.layout.wrapContentHeight
////import androidx.compose.foundation.rememberScrollState
////import androidx.compose.foundation.shape.CircleShape
////import androidx.compose.foundation.shape.CutCornerShape
////import androidx.compose.foundation.shape.RoundedCornerShape
////import androidx.compose.foundation.text.ClickableText
////import androidx.compose.foundation.verticalScroll
////import androidx.compose.material.BottomNavigation
////import androidx.compose.material.BottomNavigationItem
////import androidx.compose.material.TopAppBar
////import androidx.compose.material.icons.Icons
////import androidx.compose.material.icons.filled.AccountCircle
////import androidx.compose.material.icons.filled.Home
////import androidx.compose.material.icons.filled.LocationOn
////import androidx.compose.material.icons.filled.Menu
////import androidx.compose.material.icons.filled.Refresh
////import androidx.compose.material.icons.filled.Search
////import androidx.compose.material3.Button
////import androidx.compose.material3.ButtonDefaults
////import androidx.compose.material3.Card
////import androidx.compose.material3.CardDefaults
////import androidx.compose.material3.Icon
////import androidx.compose.material3.IconButton
////import androidx.compose.material3.MaterialTheme
////import androidx.compose.material3.Scaffold
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.getValue
////import androidx.compose.runtime.mutableStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.runtime.setValue
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.layout.ContentScale
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.text.AnnotatedString
////import androidx.compose.ui.text.TextStyle
////import androidx.compose.ui.text.font.FontFamily
////import androidx.compose.ui.text.font.FontStyle
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.ui.text.style.TextAlign
////import androidx.compose.ui.text.style.TextDecoration
////import androidx.compose.ui.tooling.preview.Preview
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.em
////import androidx.compose.ui.unit.sp
////import androidx.lifecycle.ViewModel
////import androidx.lifecycle.viewModelScope
////import androidx.navigation.NavHostController
////import androidx.navigation.compose.rememberNavController
////import com.google.android.gms.analytics.ecommerce.Product
////import kotlinx.coroutines.flow.MutableStateFlow
////import kotlinx.coroutines.flow.SharingStarted
////import kotlinx.coroutines.flow.StateFlow
////import net.ezra.R
////import net.ezra.navigation.ROUTE_ABOUT
////import net.ezra.navigation.ROUTE_ADD_STUDENTS
////import net.ezra.navigation.ROUTE_CONTACT
////import net.ezra.navigation.ROUTE_HOME
////import net.ezra.navigation.ROUTE_MENU
////import net.ezra.navigation.ROUTE_SEARCH
////import net.ezra.navigation.ROUTE_STUDENTLIST
////import net.ezra.ui.home.HomeScreen
////import net.ezra.ui.theme.defaultTextStyle
////import java.util.Locale
//
//fun WebScreen(navController: NavHostController) {
////    class FilterProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
////
////        private var _filterProductUiState: StateFlow<FilterProductUiState> = MutableStateFlow(FilterProductUiState())
////        val filterProductUiState: StateFlow<FilterProductUiState> get() = _filterProductUiState
////
////        private var isFilterActive = false
////
////        fun isFilterActive(): Boolean {
////            return isFilterActive
////        }
////
////        suspend fun applyProductFilter(
////
////            category: List<Locale.Category?>,
////            currency: String,
////            startPrice: Double,
////            endPrice: Double,
////            startDate: String,
////            endDate: String,
////            company: String
////        ) {
////            isFilterActive = true
////
////            object {
////                private val TIMEOUT_MILLIS = 5_000L
////            }
////            val _filterProductUiState: StateFlow<FilterProductUiState> = productRepository.filterProducts(
////                category = category,
////                currency = currency,
////                startPrice = startPrice,
////                endPrice = endPrice,
////                startDate = startDate,
////                endDate = endDate,
////                company = company
////            ).map { FilterProductUiState(it) }
////                .stateIn(
////                    scope = viewModelScope,
////                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
////                    initialValue = FilterProductUiState()
////                )
////
////            Log.d("FilterListComplete", "Filtered Results: ${filterProductUiState.value}")
////        }
//
//
//
//
//
//    }
//
////    data class FilterProductUiState(val filterProductList: List<Product> = listOf())
////}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewLight() {
//    WebScreen(rememberNavController())
//}