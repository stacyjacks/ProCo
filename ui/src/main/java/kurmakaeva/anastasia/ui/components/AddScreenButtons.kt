package kurmakaeva.anastasia.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kurmakaeva.anastasia.ui.R

@Composable
fun AddScreenButtons(
    onTapAdd: () -> Unit,
    onTapCancel: () -> Unit,
    saveAction: () -> Unit
) {
    Button(
        onClick = {
            saveAction()
            onTapAdd()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(text = stringResource(id = R.string.save))
    }

    TextButton(
        onClick = { onTapCancel() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.cancel),
            textAlign = TextAlign.Center
        )
    }
}