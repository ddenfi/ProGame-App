package com.ddenfi.capstonecompose.ui.view

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ddenfi.capstonecompose.domain.model.GameDetailScreenState
import com.ddenfi.capstonecompose.domain.model.GameModel

class DetailScreenDataProvider:PreviewParameterProvider<GameDetailScreenState> {
    override val values: Sequence<GameDetailScreenState>
        get() = sequenceOf(
            GameDetailScreenState(
                GameModel(
                    28,
                    "Red Dead Redemption 2",
                    4.59,
                    4369,
                    "https://media.rawg.io/media/games/511/5118aff5091cb3efec399c808f8c598f.jpg",
                    "<p>America, 1899. The end of the wild west era has begun as lawmen hunt down the last remaining outlaw gangs. Those who will not surrender or succumb are killed. </p>\n" +
                            "<p>After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own ideals and loyalty to the gang who raised him.</p>\n" +
                            "<p>From the creators of Grand Theft Auto V and Red Dead Redemption, Red Dead Redemption 2 is an epic tale of life in America at the dawn of the modern age.</p>",
                    parentPlatform = listOf(1,2,3),
                    "2012-09-25",
                    false
                ),
                isLoading = false,
                hasError = false,
                errorMessage = null
            )
        )

}