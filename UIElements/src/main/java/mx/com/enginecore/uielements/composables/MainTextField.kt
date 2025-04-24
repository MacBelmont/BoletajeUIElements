package mx.com.enginecore.uielements.composables

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.com.enginecore.uielements.R


/**
 * A custom TextField composable.
 * Uses a default theme and supports instant password and text input visualization.
 *
 * @param modifier The modifier to be applied to the TextField.
 * @param passwordIconVisible can only be [Painter] or [ImageVector] else it will be ignored.
 * @param passwordIconHidden  can only be [Painter] or [ImageVector] else it will be ignored.
 */

@Composable
fun MainTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    hint: String? = null,
    labelColor: Color = MaterialTheme.colorScheme.onSurface,
    labelStyle: TextStyle = MaterialTheme.typography.labelLarge,
    maxChars: Int = 1000,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (textInput: String) -> Unit = {},
    trailingIconResource: Int? = null,
    trailingIconImageVector: ImageVector? = null,
    onTrailingIconClick: () -> Unit = {},
    containerColor: Color = Color.White.copy(alpha = .75f),
    contentColor: Color = MaterialTheme.colorScheme.primary,
    isPassword: Boolean = false,
    passwordIconVisible: Any? = null,
    passwordIconHidden: Any? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = 1
) {

    val customModifier = modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val shape = RoundedCornerShape(25.dp)

    Column {
        label?.let {
            Text(
                label,
                style = labelStyle,
                color = labelColor
            )
        }

        Box(
            modifier = customModifier
                .fillMaxWidth()
                .border(1.dp, contentColor.copy(alpha = .55f), shape)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = {
                    if (it.length <= maxChars) {
                        onValueChange(it)
                    }
                },
                shape = shape,
                placeholder = {
                    hint?.let {
                        Text(
                            hint,
                            color = contentColor.copy(alpha = .75f)
                        )
                    }
                },
                trailingIcon = {
                    if (isPassword) {

                        //If icon is ImageVector, both variables mustn't be null
                        if (passwordIconVisible is ImageVector && passwordIconHidden is ImageVector) {

                            Icon(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { passwordVisible = !passwordVisible },
                                imageVector = if (passwordVisible) {
                                    passwordIconHidden
                                } else {
                                    passwordIconVisible
                                },
                                contentDescription = null,
                                tint = contentColor
                            )

                        } else {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { passwordVisible = !passwordVisible },
                                painter = if (passwordVisible) {
                                    if (passwordIconHidden is Painter) {
                                        passwordIconHidden
                                    } else {
                                        painterResource(id = R.drawable.eye_crossed)
                                    }
                                } else {
                                    if (passwordIconVisible is Painter) {
                                        passwordIconVisible
                                    } else {
                                        painterResource(id = R.drawable.eye)
                                    }
                                },
                                contentDescription = null,
                                tint = contentColor
                            )
                        }


                    } else {

                        if (trailingIconImageVector != null) {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { onTrailingIconClick() },
                                imageVector = trailingIconImageVector,
                                contentDescription = null,
                                tint = contentColor
                            )
                        } else {
                            trailingIconResource?.let {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable { onTrailingIconClick() },
                                    painter = painterResource(id = trailingIconResource),
                                    contentDescription = null,
                                    tint = contentColor
                                )
                            }
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    disabledTextColor = contentColor.copy(alpha = .75f),
                    focusedTextColor = contentColor,
                    unfocusedTextColor = contentColor.copy(alpha = .75f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = if (passwordVisible || !isPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                enabled = enabled,
                singleLine = singleLine,
                maxLines = maxLines,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
            )
        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CustomTextFieldPreView() {
    MainTextField(
        value = "",
        isPassword = true
    )
}