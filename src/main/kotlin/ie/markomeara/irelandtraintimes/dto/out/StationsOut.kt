package ie.markomeara.irelandtraintimes.dto.out

data class StationDto(
    val code: String,
    val name: String,
    val location: CoordinateDto)