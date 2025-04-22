package mx.com.enginecore.libs.boletajeui.elements.composables

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView


/**
 * A layout composable with [content].
 * Shows a video background that covers the entire screen.
 *
 * @param videoUri the Uri of the video to be played, must be in project's RAW directory.
 * @param overlayColor The color of the overlay that covers the video.
 */

@SuppressLint("OpaqueUnitKey")
@Composable
fun VideoBackgroundView(
    videoUri: Uri? = null,
    overlayColor: Color = Color.Black.copy(alpha = .65f),
    content: @Composable () -> Unit
) {

    val context = LocalContext.current
    var exoPlayer by remember {
        mutableStateOf<ExoPlayer?>(null)
    }

    LaunchedEffect(Unit) {
        if (videoUri != null) {
            exoPlayer = context.buildExoPlayer(videoUri)
        }
    }


    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            exoPlayer?.let { eP ->
                DisposableEffect(
                    AndroidView(
                        factory = { it.buildPlayerView(eP) },
                        modifier = Modifier.fillMaxSize()
                    )
                ) {
                    onDispose {
                        eP.release()
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(overlayColor)
            )

            content()

        }
    }


}

private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        useController = false
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }