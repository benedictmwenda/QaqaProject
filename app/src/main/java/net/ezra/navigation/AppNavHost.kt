package net.ezra.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ezra.ui.Getstarted
import net.ezra.ui.SplashScreen
import net.ezra.ui.auth.LoginScreen
import net.ezra.ui.auth.SignUpScreen
import net.ezra.ui.authentication.DashboardScreen
import net.ezra.ui.home.HomeScreen
import net.ezra.ui.home.MenuScreen
import net.ezra.ui.inputdata.AddStudents
import net.ezra.ui.inputdata.ProductDetailScreen
import net.ezra.ui.inputdata.ProductListScreen
import net.ezra.ui.inputdata.Student_list
import net.ezra.ui.searchpanel.Search
import net.ezra.ui.shop.QaqaScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH


) {
    BackHandler {
        navController.popBackStack()

        }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        composable(ROUTE_SEARCH) {
            Search(navController)
        }
        composable(ROUTE_ADD_STUDENTS) {
            AddStudents(navController)
        }

        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUTE_STUDENTLIST) {
            Student_list(navController = navController, viewModel = viewModel())
        }

        composable(ROUTE_MENU) {
            MenuScreen(navController = navController)
        }

        composable(ROUTE_QAQA) {
            QaqaScreen(title = String.Companion, navController = navController)
        }

        composable(ROUTE_GET) {
            Getstarted(navController = navController)
        }

        composable(ROUTE_PROFILE) {
            DashboardScreen(navController = navController)
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController, onLoginSuccess = { navController.navigate(ROUTE_REGISTER) {
                popUpTo(ROUTE_LOGIN) { inclusive = true }
            } })
        }

        composable(ROUTE_REGISTER) {
            SignUpScreen(navController, onSignUpSuccess = {navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_REGISTER) { inclusive = true }
            }}, darkTheme = false)
        }

        composable(ROUTE_VIEW_PROD) {
            ProductListScreen(navController = navController, products = listOf() )
        }



        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }































    }
}