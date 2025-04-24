package mx.com.enginecore.uielements.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.com.enginecore.libs.boletajeui.elements.theme.DisabledGray

@Composable
fun MainCodeInput(
    code: String = "",
    shakeController: ShakeController = rememberShakeController(),
    maskPassword: Boolean = true,
    maksCharacter: String = "•",
    codeLength: Int = 4,
    onValueChange: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0..<codeLength) {
                CodeInputBoxItem(
                    position = i,
                    fullValue = code,
                    shakeController = shakeController,
                    maskPassword = maskPassword,
                    maskCharacter = maksCharacter
                )
            }
        }

        InvisibleTextField(
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Number
            ),
            value = code
        ) {
            onValueChange(it)
        }

    }
}


@Composable
internal fun InvisibleTextField(
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Transparent,
            unfocusedTextColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            errorTextColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = Color.Transparent,
            errorCursorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = Color.Transparent,
            unfocusedLeadingIconColor = Color.Transparent,
            disabledLeadingIconColor = Color.Transparent,
            errorLeadingIconColor = Color.Transparent,
            focusedTrailingIconColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.Transparent,
            disabledTrailingIconColor = Color.Transparent,
            errorTrailingIconColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            disabledLabelColor = Color.Transparent,
            errorLabelColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.Transparent,
            disabledPlaceholderColor = Color.Transparent,
            errorPlaceholderColor = Color.Transparent,
            focusedSupportingTextColor = Color.Transparent,
            unfocusedSupportingTextColor = Color.Transparent,
            disabledSupportingTextColor = Color.Transparent,
            errorSupportingTextColor = Color.Transparent,
            focusedPrefixColor = Color.Transparent,
            unfocusedPrefixColor = Color.Transparent,
            disabledPrefixColor = Color.Transparent,
            errorPrefixColor = Color.Transparent,
            focusedSuffixColor = Color.Transparent,
            unfocusedSuffixColor = Color.Transparent,
            disabledSuffixColor = Color.Transparent,
            errorSuffixColor = Color.Transparent
        )
    )
}

@Composable
fun CodeInputBoxItem(
    position: Int,
    fullValue: String?,
    maskCharacter: String = "•",
    shakeController: ShakeController,
    maskPassword: Boolean = true
) {
    Box(
        modifier = Modifier
            .height(55.dp)
            .aspectRatio(1f)
            .shake(shakeController)
            .clip(CircleShape)
            .background(DisabledGray.copy(alpha = .65f))
            .border(2.dp, Color.Black.copy(alpha = .5f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text =
            if (fullValue != null) {
                if (fullValue.length >= (position + 1)) {
                    if (!maskPassword) {
                        fullValue[position].toString()
                    } else {
                        maskCharacter
                    }
                } else {
                    ""
                }
            } else {
                ""
            },
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}


@Preview
@Composable
fun CodeInputFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        MainCodeInput(
            code = "1234",
            maskPassword = false,
            shakeController = rememberShakeController()
        )
    }
}