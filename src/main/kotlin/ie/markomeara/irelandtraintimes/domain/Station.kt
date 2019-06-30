package ie.markomeara.irelandtraintimes.domain

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