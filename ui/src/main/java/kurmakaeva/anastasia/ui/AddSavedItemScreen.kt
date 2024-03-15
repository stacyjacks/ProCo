package kurmakaeva.anastasia.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.Typography
import kurmakaeva.anastasia.ui.viewmodel.AddSavedItemViewModel

@Composable
fun AddSavedItemScreen(viewModel: AddSavedItemViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarTitle(screen = "New preset")
        AddSavedItem(
            amount = viewModel.savedItem.grams.toString(),
            onAmountChanged = { viewModel.onAmountChanged(it.toFloat()) },
            name = viewModel.savedItem.name,
            onNameChanged = { viewModel.onNameChanged(it) }
        )
        Button(
            onClick = { viewModel.addSavedItem() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun AddSavedItem(
    amount: String,
    onAmountChanged: (String) -> Unit,
    name: String,
    onNameChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = if (amount != "0.0") amount else "",
            onValueChange = { onAmountChanged(it) },
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .width(100.dp)
                .height(100.dp),
            placeholder = {
                Text(
                    text = "0",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    style = Typography.titleLarge
                )
            },
            singleLine = true,
            textStyle = Typography.titleLarge
        )

        Text(
            text = "grams",
            modifier = Modifier.padding(16.dp),
            style = Typography.bodyLarge
        )

        TextField(
            value = name,
            onValueChange = { onNameChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            textStyle = Typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun AddSavedItemPreview() {
    AddSavedItem(
        amount = "",
        onAmountChanged = { /* preview only */ },
        name = "Protein shake",
        onNameChanged = { /* preview only */ }
    )
}