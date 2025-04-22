package mx.com.enginecore.libs.boletajeui.elements.composables

import android.icu.util.TimeZone
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mx.com.enginecore.libs.boletajeui.utils.Utils.Companion.convertMillisToDate
import mx.com.enginecore.libs.boletajeui.utils.Utils.Companion.getCurrentTimeMills
import java.time.Instant
import java.time.ZoneId

class MainDatePickerDialog {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDatePickerDialog(
    onDismissRequest: () -> Unit = {},
    onDateSelected: (String, Long) -> Unit = { _, _ -> },
    onlyPastDates: Boolean = false,
    minimumDate: Long? = getCurrentTimeMills(),
    startingDate: Long? = null
) {


    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = startingDate ?: getCurrentTimeMills(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (onlyPastDates) {
                    utcTimeMillis < getCurrentTimeMills()
                } else {
                    if (minimumDate != null) {
                        utcTimeMillis >= (minimumDate)
                    } else {
                        true
                    }
                }
            }
        })

    var selectedDateMills = 0L
    val selectedDate = datePickerState.selectedDateMillis?.let { selectedDateMillis ->
        val seconds = Instant
            .ofEpochMilli(selectedDateMillis)
            .atZone(ZoneId.systemDefault())
            .toEpochSecond()
        val offset: Int = TimeZone.getDefault().rawOffset + TimeZone.getDefault().dstSavings
        val adjust: Long = selectedDateMillis - offset
        println(
            "selectedDate_datePicker 1: $adjust - ${
                convertMillisToDate(
                    adjust,
                    "dd/MM/yyyy HH:mm:ss"
                )
            }"
        )
        println(
            "selectedDate_datePicker 2: $selectedDateMillis - ${
                convertMillisToDate(
                    selectedDateMillis,
                    "dd/MM/yyyy HH:mm:ss"
                )
            }"
        )

        selectedDateMills = adjust
        convertMillisToDate(adjust) ?: ""
    }

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(selectedDate.toString(), datePickerState.selectedDateMillis ?: 0L)
                onDismissRequest()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }


}


@Preview
@Composable
fun CustomDatePickerDialogPreview() {
    MainDatePickerDialog(
        onlyPastDates = true
    )
}