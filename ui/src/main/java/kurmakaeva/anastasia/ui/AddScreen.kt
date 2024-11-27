package kurmakaeva.anastasia.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
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
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.theme.Purple40
import kurmakaeva.anastasia.ui.theme.Typography
import kurmakaeva.anastasia.ui.viewmodel.AddViewModel

@Composable
fun AddScreen(
    type: ScreenType,
    onTapAdd: () -> Unit,
    onTapCancel: () -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    AddScreenView(
        type = type,
        onTapAdd = onTapAdd,
        onTapCancel = onTapCancel,
        onAmountChanged = {
            when (type) {
                ScreenType.AddGoal -> viewModel.onGoalAmountChanged(it.toFloat())
                ScreenType.AddInput -> viewModel.onInputAmountChanged(it.toFloat())
                ScreenType.AddSaved -> viewModel.onSavedAmountChanged(it.toFloat())

                else -> { /* do nothing */ }
            }
        },
        goal = viewModel.goal.goal.toString(),
        input = viewModel.input.input.toString(),
        saved = viewModel.savedItem.grams.toString(),
        savedName = viewModel.savedItem.name,
        onSavedNameChanged = { viewModel.onNameChanged(it) },
        onTapSave = {
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

@Composable
private fun AddScreenView(
    type: ScreenType,
    onTapAdd: () -> Unit,
    onTapCancel: () -> Unit,
    onAmountChanged: (String) -> Unit,
    goal: String? = null,
    input: String? = null,
    saved: String? = null,
    savedName: String? = null,
    onSavedNameChanged: ((String) -> Unit)? = null,
    onTapSave: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarTitle(screen = type)

        AddGramsContainer(
            amount = getAmount(
                type = type,
                goal = goal.orEmpty(),
                input = input.orEmpty(),
                saved = saved.orEmpty()
                ),
            onAmountChanged = {
                onAmountChanged(it)
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

        if (type == ScreenType.AddSaved && onSavedNameChanged != null) {
            AddNameContainer(
                name = savedName.orEmpty(),
                onNameChanged = { onSavedNameChanged(it) }
            )
        }
        AddScreenButtons(
            onTapAdd = { onTapAdd() },
            onTapCancel = { onTapCancel() },
            saveAction = {
                onTapSave()
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
                    color = Purple40.copy(alpha = 0.6f),
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
private fun AddNameContainer(
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

@Preview
@Composable
private fun AddSavedItemPreview() {
    ProCoTheme {
        AddScreenView(
            type = ScreenType.AddSaved,
            onTapAdd = {},
            onTapCancel = {},
            onAmountChanged = {},
            onSavedNameChanged = {},
            onTapSave = {}
        )
    }
}