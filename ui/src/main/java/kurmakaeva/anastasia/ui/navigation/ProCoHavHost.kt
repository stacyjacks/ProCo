package kurmakaeva.anastasia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import kurmakaeva.anastasia.ui.AddScreen
import kurmakaeva.anastasia.ui.AddScreenType
import kurmakaeva.anastasia.ui.DashboardScreen
import kurmakaeva.anastasia.ui.SavedItemsScreen

private const val DashboardRoute = "dashboard"
private const val SavedRoute = "saved"
private const val AddSavedRoute = "addSaved"
private const val AddGoalRoute = "addGoal"

@Composable
fun ProCoNavHost(navController: NavHostController) {
    val navGraph = navController.createGraph(startDestination = DashboardRoute) {
        composable(route = DashboardRoute) {
            DashboardScreen(
                onNavigateToSaved = { navController.navigate(route = SavedRoute) },
                onNavigateToAdd =  { navController.navigate(route = AddSavedRoute) },
                onClickProgress = { navController.navigate(route = AddGoalRoute) }
            )
        }
        composable(route = SavedRoute) {
            SavedItemsScreen()
        }
        composable(route = AddSavedRoute) {
            AddScreen(
                type = AddScreenType.Saved,
                onTapAdd = {
                    navController.popBackStack()
                    navController.navigate(SavedRoute)
                }
            )
        }
        composable(route = AddGoalRoute) {
            AddScreen(
                type = AddScreenType.Goal,
                onTapAdd = {
                    navController.navigate(DashboardRoute) {
                        popUpTo(DashboardRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

    NavHost(navController = navController, graph = navGraph)
}

