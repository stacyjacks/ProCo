package kurmakaeva.anastasia.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kurmakaeva.anastasia.ui.R

@Composable
fun AlertDialog(
    icon: ImageVector,
    title: String,
    text: String,
    show: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        AlertDialog(
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.warningContentDesc)
                )
            },
            title = { Text(text = title) },
            text = {
                Text(text = text)
            },
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = stringResource(id = R.string.yes).uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(id = R.string.cancel).uppercase())
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewDialog() {
    AlertDialog(
        icon = Icons.Default.Warning,
        title = "Reset warning",
        text = "Are you sure you want to reset your daily input?",
        show = true,
        onConfirm = { /* preview only */ },
        onDismiss = { /* preview only */ }
    )
}