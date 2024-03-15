package kurmakaeva.anastasia.proco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import kurmakaeva.anastasia.ui.AddSavedItemScreen
import kurmakaeva.anastasia.ui.DashboardScreen
import kurmakaeva.anastasia.ui.SavedItemsScreen
import kurmakaeva.anastasia.ui.theme.ProCoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProCoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SavedItemsScreen()
                    //AddSavedItemScreen()
                    //DashboardScreen()
                }
            }
        }
    }
}