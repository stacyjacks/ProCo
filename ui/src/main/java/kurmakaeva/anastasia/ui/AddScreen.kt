package kurmakaeva.anastasia.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.Typography
import kurmakaeva.anastasia.ui.viewmodel.AddViewModel

@Composable
fun AddScreen(
    type: ScreenType,
    onTapAdd: () -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarTitle(screen = type)

        AddGramsContainer(
            amount = getAmount(type, viewModel),
            onAmountChanged = {
                when (type) {
                    ScreenType.AddGoal -> viewModel.onGoalAmountChanged(it.toFloat())
                    ScreenType.AddInput -> viewModel.onInputAmountChanged(it.toFloat())
                    ScreenType.AddSaved -> viewModel.onSavedAmountChanged(it.toFloat())

                    else -> { /* do nothing */ }
                }
            }
        )

        if (type == ScreenType.AddGoal) {
            Text(
                text = "per day",
                modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.bodyLarge
            )
        }

        if (type == ScreenType.AddSaved) {
            AddNameContainer(
                name = viewModel.savedItem.name,
                onNameChanged = { viewModel.onNameChanged(it) }
            )
        }

        Button(
            onClick = {
                when (type) {
                    ScreenType.AddSaved -> {
                        viewModel.addSavedItem()
                    }
                    ScreenType.AddInput -> {
                        viewModel.addInput()
                    }
                    ScreenType.AddGoal -> {
                        viewModel.addGoal()
                    }
                    else -> { /* do nothing */ }
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
            onValueChange = {
                if (it.isEmpty() || it.toDoubleOrNull() != null) {
                    onAmountChanged(it)
                }
            },
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
            textStyle = Typography.titleLarge,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
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
        textStyle = Typography.bodyLarge,
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}

private fun getAmount(type: ScreenType, viewModel: AddViewModel): String {
    return when (type) {
        ScreenType.AddGoal -> viewModel.goal.goal.toString()
        ScreenType.AddInput -> viewModel.input.input.toString()
        ScreenType.AddSaved -> viewModel.savedItem.grams.toString()
        else -> { "" }
    }
}


@Preview
@Composable
fun AddSavedItemPreview() {
    AddGramsContainer(
        amount = "",
        onAmountChanged = { /* preview only */ },
    )
}