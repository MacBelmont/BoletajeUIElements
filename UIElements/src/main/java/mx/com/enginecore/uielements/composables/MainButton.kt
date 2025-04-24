package mx.com.enginecore.uielements.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    leadingImage: Any? = null,
    leadingIcon: ImageVector? = null,
    text: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true,
    outlined: Boolean = false,
    textButton: Boolean = false,
    underlined: Boolean = false,
    isLoading: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primary
) {

    val borderStroke = if (outlined) {
        BorderStroke(width = 1.dp, color = color)
    } else {
        null
    }

    val containerColor = if (outlined || textButton) Color.Transparent else color
    val contentColor = if (outlined || textButton) {
        color
    } else {
        MaterialTheme.colorScheme.onPrimary
    }
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        ),
        contentPadding = if (textButton) PaddingValues(2.dp) else ButtonDefaults.ContentPadding,
        border = borderStroke,
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(8.dp),
        enabled = enabled
    ) {
        if (!isLoading) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingImage != null || leadingIcon != null) {
                    leadingImage?.let {
                        MainImageContainer(
                            modifier = Modifier.size(25.dp),
                            imageData = leadingImage
                        )
                    }
                    leadingIcon?.let {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = contentColor
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    textDecoration = if (underlined) TextDecoration.Underline else TextDecoration.None,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = contentColor,
                    strokeWidth = 2.dp
                )
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CustomButtonPreview() {

    MainButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        text = "Main Button",
        leadingIcon = Icons.Default.ArrowBack,
        onClick = {}
    )


}