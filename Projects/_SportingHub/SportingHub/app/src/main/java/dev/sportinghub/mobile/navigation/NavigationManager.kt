package dev.sportinghub.mobile.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.sportinghub.mobile.server.components.AppNavigationBar
import dev.sportinghub.mobile.viewmodels.ClientVM
import dev.sportinghub.mobile.views.authentication.EmailChangeScreen
import dev.sportinghub.mobile.views.authentication.PasswordChangeScreen
import dev.sportinghub.mobile.views.authentication.SignInScreen
import dev.sportinghub.mobile.views.authentication.SignUpScreen
import dev.sportinghub.mobile.views.detail.OrderDetailScreen
import dev.sportinghub.mobile.views.detail.PublicationDetailScreen
import dev.sportinghub.mobile.views.detail.ReportDetailScreen
import dev.sportinghub.mobile.views.navbar.account.AccountScreen
import dev.sportinghub.mobile.views.navbar.account.AppSettingsScreen
import dev.sportinghub.mobile.views.navbar.account.ProfileSettingsScreen
import dev.sportinghub.mobile.views.navbar.home.AboutUsScreen
import dev.sportinghub.mobile.views.navbar.home.HomeScreen
import dev.sportinghub.mobile.views.navbar.home.NotificationsListScreen
import dev.sportinghub.mobile.views.navbar.orders.OrdersListScreen
import dev.sportinghub.mobile.views.navbar.reports.ReportsListScreen
import dev.sportinghub.mobile.views.navbar.search.SearchResultScreen
import dev.sportinghub.mobile.views.navbar.search.SearchScreen
import dev.sportinghub.mobile.views.orders.MyBagScreen
import dev.sportinghub.mobile.views.util.ErrorScreen
import dev.sportinghub.mobile.views.util.LoadScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigate(clientVM: ClientVM) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    Scaffold(
        bottomBar = {
            val showBottomBar = currentRoute in when(clientVM.getClientType().toString().trim().uppercase()) {
                "PERSONAL" -> listOf("Home","Search","MyBag","Account")
                "BRAND" -> listOf("OrdersList","Account")
                "ADMIN" -> listOf("ReportsList","Search")
                else -> emptyList()
            }
            if (showBottomBar) AppNavigationBar(navController,clientVM)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "FirstLoad",
            modifier = Modifier.padding(innerPadding)
        ) {
            // AUTHENTICATION
            composable("EmailChange") {
                EmailChangeScreen(clientVM, navController)
            }
            composable("PasswordChange") {
                PasswordChangeScreen(clientVM, navController)
            }
            composable("SignIn") {
                SignInScreen(clientVM,navController)
            }
            composable("SignUp") {
                SignUpScreen(clientVM, navController)
            }
            // DETAIL
            composable("OrderDetail") {
                OrderDetailScreen(clientVM, navController)
            }

            composable(
                "publication_detail_screen/{publicationId}",
                arguments = listOf(navArgument("publicationId") { type = NavType.IntType })
            ) { backStackEntry ->

                val publicationId = backStackEntry.arguments?.getInt("publicationId")
                if (publicationId == null) return@composable

                // No vuelvas a instanciar clientVM, ya lo tienes como parámetro del NavHost
                PublicationDetailScreen(
                    clientVM = clientVM,
                    navController = navController,
                    id = publicationId
                )
                PublicationDetailScreen(
                    clientVM = clientVM,
                    navController = navController,
                    id = publicationId
                )
            }

            composable("ReportDetail") {
                ReportDetailScreen(clientVM, navController)
            }
            // NAVBAR - ACCOUNT
            composable("Account") {
                AccountScreen(clientVM, navController)
            }
            composable("AppSettings") {
                AppSettingsScreen(clientVM, navController)
            }
            composable("ProfileSettings") {
                ProfileSettingsScreen(clientVM, navController)
            }
            // NAVBAR - HOME
            composable("AboutUs") {
                AboutUsScreen(clientVM, navController)
            }
            composable("Home") {
                HomeScreen(clientVM, navController)
            }
            composable("MyBag") {
                MyBagScreen(clientVM, navController)
            }
            composable("NotificationList") {
                NotificationsListScreen(clientVM, navController)
            }
            // NAVBAR - ORDERS
            composable("OrdersList") {
                OrdersListScreen(clientVM, navController)
            }
            // NAVBAR - REPORTS
            composable("ReportsList") {
                ReportsListScreen(clientVM, navController)
            }
            // NAVBAR - SEARCH
            composable("Search") {
                SearchScreen(clientVM, navController)
            }
            composable("SearchResult") {
                SearchResultScreen(clientVM, navController)
            }
            // UTIL
            composable("FirstLoad") {
                val context = LocalContext.current
                LoadScreen(clientVM,navController,"SignIn",{clientVM.loadDefaultData(context)})
            }
            composable("Error/{Destination}?{OnLoad}") { backStackEntry ->
                val destination = backStackEntry.arguments?.getString("Destination") ?: "Load"
                val onLoad: (suspend () -> Unit)? = when(backStackEntry.arguments?.getString("OnLoad")) {
                    else -> null
                }
                ErrorScreen(clientVM, navController, destination,onLoad)
            }
            composable("Load/{Destination}?{OnLoad}") { backStackEntry ->
                val destination = backStackEntry.arguments?.getString("Destination") ?: "SignIn"
                val onLoad: (suspend () -> Unit)? = when(backStackEntry.arguments?.getString("OnLoad")) {
                    else -> null
                }
                LoadScreen(clientVM,navController,destination,onLoad)
            }
        }
    }
}
