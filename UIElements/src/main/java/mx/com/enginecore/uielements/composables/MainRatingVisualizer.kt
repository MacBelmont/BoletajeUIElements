package mx.com.enginecore.uielements.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainRatingVisualizer(
    currentRating: Int = 0,
    outOf: Int = 5,
    color: Color = Color.Yellow,
    filledIcon: ImageVector = Icons.Filled.Favorite,
    outlinedIcon: ImageVector = Icons.Filled.FavoriteBorder,
    onRatingClick: () -> Unit = {}
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        //Full stars
        for (i in 1..currentRating) {
            Icon(
                imageVector = filledIcon,
                contentDescription = null,
                tint = color
            )
        }
        //Empty stars
        for (currentRating in (currentRating + 1)..outOf) {
            Icon(
                imageVector = outlinedIcon,
                contentDescription = null,
                tint = color
            )
        }

    }

}

@Preview
@Composable
fun RatingVisualizerPreview() {

    MainRatingVisualizer(
        currentRating = 4
    )

}