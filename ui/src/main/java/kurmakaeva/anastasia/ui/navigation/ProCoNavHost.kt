package kurmakaeva.anastasia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import kurmakaeva.anastasia.ui.AddScreen
import kurmakaeva.anastasia.ui.DashboardScreen
import kurmakaeva.anastasia.ui.SavedItemsScreen
import kurmakaeva.anastasia.ui.ScreenType

private const val DashboardRoute = "dashboard"
private const val SavedRoute = "saved"
private const val AddSavedRoute = "addSaved"
private const val AddGoalRoute = "addGoal"
private const val AddInputRoute = "addInput"

@Composable
fun ProCoNavHost(navController: NavHostController) {
    val navGraph = navController.createGraph(startDestination = DashboardRoute) {
        composable(route = DashboardRoute) {
            DashboardScreen(
                onNavigateToSaved = { navController.navigate(route = SavedRoute) },
                onNavigateToAdd =  { navController.navigate(route = AddInputRoute) },
                onClickProgress = { navController.navigate(route = AddGoalRoute) }
            )
        }
        composable(route = SavedRoute) {
            SavedItemsScreen(
                onNavigateToAdd =  { navController.navigate(route = AddSavedRoute) }
            )
        }
        composable(route = AddSavedRoute) {
            AddScreen(
                type = ScreenType.AddSaved,
                onTapAdd = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = AddGoalRoute) {
            AddScreen(
                type = ScreenType.AddGoal,
                onTapAdd = {
                    navController.navigate(DashboardRoute) {
                        popUpTo(DashboardRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = AddInputRoute) {
            AddScreen(
                type = ScreenType.AddInput,
                onTapAdd = {
                    navController.popBackStack()
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

