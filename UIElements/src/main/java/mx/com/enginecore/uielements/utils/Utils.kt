package mx.com.enginecore.uielements.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Date

public class Utils {

    companion object {
        fun getCurrentTimeMills(): Long {
            val currentInstant = Instant.now()
            val localTime = currentInstant.atZone(ZoneId.systemDefault())
            return localTime.toInstant().toEpochMilli()
        }

        @SuppressLint("SimpleDateFormat")
        fun convertMillisToDate(
            millis: Long?,
            format: String = "dd/MM/yyyy"
        ): String? {
            millis?.let {
                val currentInstant = Instant.now()
                val formatter = SimpleDateFormat(format)
                return formatter.format(Date(millis))
            }
            return null
        }

    }

}