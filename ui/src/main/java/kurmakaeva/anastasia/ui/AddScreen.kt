package kurmakaeva.anastasia.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import kurmakaeva.anastasia.ui.viewmodel.AddViewModel

@Composable
fun AddScreen(
    type: AddScreenType,
    onTapAdd: () -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarTitle(screen = if (type == AddScreenType.Goal) "Add goal" else "New preset")
        AddGramsContainer(
            amount = viewModel.savedItem.grams.toString(),
            onAmountChanged = { viewModel.onAmountChanged(it.toFloat()) }
        )

        if (type == AddScreenType.Saved) {
            AddNameContainer(
                name = viewModel.savedItem.name,
                onNameChanged = { viewModel.onNameChanged(it) }
            )
        }

        Button(
            onClick = {
                if (type == AddScreenType.Saved) {
                    viewModel.addSavedItem()
                } else {
                    viewModel.addGoal()
                }

                onTapAdd()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun AddGramsContainer(
    amount: String,
    onAmountChanged: (String) -> Unit
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
    }
}

@Composable
fun AddNameContainer(
    name: String,
    onNameChanged: (String) -> Unit
) {
    TextField(
        value = name,
        onValueChange = { onNameChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        placeholder = {
            Text(
                text = "Name",
                modifier = Modifier.fillMaxWidth(),
                style = Typography.bodyLarge
            )
        },
        textStyle = Typography.bodyLarge
    )
}


@Preview
@Composable
fun AddSavedItemPreview() {
    AddGramsContainer(
        amount = "",
        onAmountChanged = { /* preview only */ },
    )
}