package kurmakaeva.anastasia.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(): ViewModel() {
    var goal by mutableStateOf(80.0f)
        private set

    var current by mutableStateOf(60.0f)
        private set


}