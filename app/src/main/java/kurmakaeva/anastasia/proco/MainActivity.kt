package kurmakaeva.anastasia.proco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kurmakaeva.anastasia.ui.navigation.ProCoNavHost
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.theme.themeGradient

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProCoTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(themeGradient)
                ) {
                    ProCoNavHost(navController = rememberNavController())
                }
            }
        }
    }
}