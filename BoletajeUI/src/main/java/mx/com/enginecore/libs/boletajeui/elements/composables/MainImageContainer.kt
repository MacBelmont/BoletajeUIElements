package mx.com.enginecore.libs.boletajeui.elements.composables

import android.os.Build
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import mx.com.enginecore.libs.boletajeui.R

@Composable
fun MainImageContainer(
    modifier: Modifier,
    imageData: Any?,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    imagePlaceholder: Int = R.drawable.default_placeholder_imagen,
    imageError: Int = R.drawable.default_error_image,
    forceBase64: Boolean = false
) {

    val REGEX_BASE64 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?\$"

    if (imageData is Int) {
        Image(
            modifier = modifier,
            painter = painterResource(id = imageData),
            contentDescription = null
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    if (imageData is String &&
                        (REGEX_BASE64.toRegex().containsMatchIn(imageData) || forceBase64)
                    ) {
                        Base64.decode(imageData, Base64.DEFAULT)
                    } else {
                        imageData
                    }
                )
                .decoderFactory(
                    if (Build.VERSION.SDK_INT >= 28) {
                        ImageDecoderDecoder.Factory()
                    } else {
                        GifDecoder.Factory()
                    }
                )
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .placeholder(imagePlaceholder)
                .error(imageError)
                .build(),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}