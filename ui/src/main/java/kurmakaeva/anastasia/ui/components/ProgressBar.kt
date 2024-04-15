package kurmakaeva.anastasia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    goal: Float,
    current: Float,
    goalText: String,
    onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(24.dp), color = Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(vertical = 64.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "0"
                )
                Box {
                    LinearProgressIndicator(
                        progress = { current.div(goal) },
                        modifier = Modifier
                            .width(this@BoxWithConstraints.maxWidth - 90.dp)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                            .height(20.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = Color.LightGray,
                        strokeCap = StrokeCap.Round,
                    )

                    Text(
                        text = if (current < goal) current.toInt().toString() else "",
                        modifier = Modifier.width(
                            (this@BoxWithConstraints.maxWidth - 90.dp) * (current.div(goal))
                        ),
                        textAlign = TextAlign.End,
                        color = Color.White
                    )
                }
                Text(text = goal.toInt().toString())
            }

            Text(
                text = goalText,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ProgressBarPreview() {
    ProgressBar(
        goal = 100f,
        current = 90f,
        goalText = "Nice! Keep going",
        onClick = { /* preview only */ }
    )
}