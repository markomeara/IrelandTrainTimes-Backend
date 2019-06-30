package ie.markomeara.irelandtraintimes.domain

data class Train(
    val code: String,
    val positionInfo: TrainPositionInfo
)