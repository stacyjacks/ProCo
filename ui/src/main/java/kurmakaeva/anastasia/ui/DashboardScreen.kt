package kurmakaeva.anastasia.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.ProgressBar
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        TopBarTitle(screen = "Today's goal")

        if (viewModel.goal != 0.0f) {
            ProgressBar(
                goal = viewModel.goal,
                current = viewModel.current,
                goalText = goalText(viewModel.current.div(100))
            )
        }

        ListOfItems()
    }
}

private fun goalText(current: Float): String {
    return when {
        current < 0.5f -> "You're off to a good start!"
        current >= 0.5f && current < 0.75f -> "Nice! Keep going."
        current >= 0.75f && current < 1.0f -> "Almost there!"
        else -> "Well done! Come back tomorrow."
    }
}

@Composable
fun ListOfItems() {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Text(text = "24 grams")
        Text(text = "24 grams")
        Text(text = "24 grams")
        Text(text = "24 grams")
    }
}

@Preview
@Composable
fun DashboardPreview() {
    ProCoTheme {
        Column {
            TopBarTitle(screen = "Today's goal")
            ProgressBar(goal = 80.0f, current = 60.0f, goalText(60.0f.div(100)))
            ListOfItems()
        }
    }
}