package kurmakaeva.anastasia.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.AddScreenButtons
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.Typography
import kurmakaeva.anastasia.ui.theme.themeGradient
import kurmakaeva.anastasia.ui.viewmodel.AddViewModel

@Composable
fun AddScreen(
    type: ScreenType,
    onTapAdd: () -> Unit,
    onTapCancel: () -> Unit,
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
                text = stringResource(id = R.string.perDay),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
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
        AddScreenButtons(
            onTapAdd = { onTapAdd() },
            onTapCancel = { onTapCancel() },
            saveAction = {
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
            }
        )
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
        TextField(
            value = if (amount != "0.0") amount else "",
            onValueChange = {
                if (it.isEmpty() || it.toDoubleOrNull() != null) {
                    onAmountChanged(it)
                }
            },
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .background(Color.Transparent),
            placeholder = {
                Text(
                    text = "0",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    style = Typography.headlineLarge
                )
            },
            singleLine = true,
            textStyle = Typography.headlineLarge,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Text(
            text = stringResource(id = R.string.grams),
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
                text = stringResource(id = R.string.savedName),
                modifier = Modifier.fillMaxWidth(),
                style = Typography.bodyLarge
            )
        },
        textStyle = Typography.bodyLarge,
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
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
    Column(modifier = Modifier.background(themeGradient).fillMaxHeight()) {
        TopBarTitle(screen = ScreenType.AddInput)
        AddGramsContainer(
            amount = "",
            onAmountChanged = { /* preview only */ },
        )
        AddScreenButtons(onTapAdd = {}, onTapCancel = {}, saveAction = {})
    }
}