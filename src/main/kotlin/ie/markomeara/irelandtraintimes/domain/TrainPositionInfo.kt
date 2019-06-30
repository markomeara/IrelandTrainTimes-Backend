package ie.markomeara.irelandtraintimes.domain

data class TrainPositionInfo(
    val code: String,
    val status: String, // TODO Enum
    val position: Coordinate,
    val publicMessage: String,
    val direction: String)