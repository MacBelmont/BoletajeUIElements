package mx.com.enginecore.boletajeuielements

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.com.enginecore.boletajeuielements.ui.theme.BoletajeUIElementsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoletajeUIElementsTheme {
                Column {
                    Greeting(
                        name = "Android",
                        videoUri = getVideoUri()
                    )

                }
            }
        }
    }

    private fun getVideoUri(): Uri? {
        return try {
            val rawId = resources.getIdentifier("video_home", "raw", packageName)
            val videoUri = "android.resource://$packageName/$rawId"
            Uri.parse(videoUri)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    videoUri: Uri? = null
) {
    var inputNameValue by remember { mutableStateOf(name) }
    var inputPasswordValue by remember { mutableStateOf("") }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoletajeUIElementsTheme {
        Greeting("Android")
    }
}