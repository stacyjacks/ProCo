package kurmakaeva.anastasia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import kurmakaeva.anastasia.ui.AddSavedItemScreen
import kurmakaeva.anastasia.ui.DashboardScreen
import kurmakaeva.anastasia.ui.SavedItemsScreen

private const val DashboardRoute = "dashboard"
private const val SavedRoute = "saved"
private const val AddSavedRoute = "addSaved"

@Composable
fun ProCoNavHost(navController: NavHostController) {
    val navGraph = navController.createGraph(startDestination = DashboardRoute) {
        composable(route = DashboardRoute) {
            DashboardScreen(
                onNavigateToSaved = { navController.navigate(route = SavedRoute) },
                onNavigateToAdd =  { navController.navigate(route = AddSavedRoute) }
            )
        }
        composable(route = SavedRoute) {
            SavedItemsScreen()
        }
        composable(route = AddSavedRoute) {
            AddSavedItemScreen(
                onTapAdd = {
                    navController.popBackStack()
                    navController.navigate(SavedRoute)
                }
            )
        }
    }

    NavHost(navController = navController, graph = navGraph)
}

