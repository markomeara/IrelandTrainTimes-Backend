package ie.markomeara.irelandtraintimes.domain

data class Coordinate(val latitude: Double,
                      val longitude: Double)

data class Station(
    val code: String,
    val id: Int,
    val names: StationName,
    val location: Coordinate
)

data class StationName(
    val description: String,
    val alias: String?
)

data class Train(
    val code: String,
    val positionInfo: TrainPositionInfo
)

data class TrainPositionInfo(
    val code: String,
    val status: String, // TODO Enum
    val position: Coordinate,
    val publicMessage: String,
    val direction: String)