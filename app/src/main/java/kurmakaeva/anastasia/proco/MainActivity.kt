package kurmakaeva.anastasia.proco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kurmakaeva.anastasia.ui.navigation.ProCoNavHost
import kurmakaeva.anastasia.ui.theme.ProCoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProCoTheme {
                ProCoNavHost(navController = rememberNavController())
            }
        }
    }
}