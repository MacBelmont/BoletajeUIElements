package mx.com.enginecore.uielements.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDialog(
    dialogTitle: String? = null,
    dialogMessage: String? = null,
    showCircularProgress: Boolean = false,
    dialogConfirmButton: String? = null,
    dialogConfirmAction: () -> Unit = {},
    dialogDismissButton: String? = null,
    dialogIconImageVector: ImageVector? = null,
    dialogIconResource: Int? = null,
    dialogDismissAction: () -> Unit = {},
    canDismiss: Boolean = true,
    isLoading: Boolean = false
) {


    val lazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {

    }

    BasicAlertDialog(
        properties = DialogProperties(
            dismissOnClickOutside = canDismiss,
            dismissOnBackPress = canDismiss
        ),
        content = {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    dialogIconImageVector?.let {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.secondary,
                            imageVector = it,
                            contentDescription = null
                        )
                    }
                    if (dialogIconImageVector == null) {
                        dialogIconResource?.let {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.primary,
                                painter = painterResource(id = it),
                                contentDescription = null
                            )
                        }
                    }

                    dialogTitle?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    dialogMessage?.let {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            if (showCircularProgress) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(15.dp),
                                    color = MaterialTheme.colorScheme.secondary,
                                    strokeWidth = 2.dp
                                )
                            }

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = it,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleMedium
                            )

                        }
                    }



                    if (!isLoading) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            dialogDismissButton?.let {
                                Button(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .weight(1f),
                                    onClick = { dialogDismissAction() },
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                        containerColor = Color.Transparent
                                    ),
                                ) {
                                    Text(text = it)
                                }
                            }

                            dialogConfirmButton?.let {
                                Button(
                                    modifier = Modifier
                                        .weight(1f),
                                    onClick = { dialogConfirmAction() },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(text = it)
                                }
                            }

                        }
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier.size(30.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 2.dp
                        )
                    }

                }
            }

        },
        onDismissRequest = {
            dialogDismissAction()
        }

    )

}

@Preview
@Composable
fun SimpleDialogPreview() {

    MainDialog(
            dialogTitle = "Procesando",
            dialogIconImageVector = Icons.Filled.Add,
            dialogMessage = "Se esta procesando tu consulta, esto puede llevar varios segundos...",
            showCircularProgress = true,
            dialogConfirmButton = "Aceptar"
        )

}