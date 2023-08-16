package com.example.githubapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubapp.features.detail.DetailScreen
import com.example.githubapp.features.search.SearchScreen
import com.example.githubapp.features.users.UsersScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavHost(
    startDestination: String = Screens.USERS.route,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.USERS.route) {
            UsersScreen(
                onUserClicked = { userName ->
                    navController.navigate("${Screens.DETAIL.route}/$userName")
                },
                onSearchClicked = {
                    navController.navigate(Screens.SEARCH.route)
                }
            )
        }

        composable(route = Screens.DETAIL.route + "/{userName}") { backStack ->
            val userLogin = backStack.arguments?.getString("userName") ?: ""
            DetailScreen(
                userName = userLogin,
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(route = Screens.SEARCH.route) {
            SearchScreen(
                onBackClicked = { navController.popBackStack() },
                onCardClicked = { userName ->
                    navController.navigate("${Screens.DETAIL.route}/$userName")
                }
            )
        }
    }
}
