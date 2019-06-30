package ie.markomeara.irelandtraintimes.dto.out

data class TrainPositionInfoDto(val code: String,
                                val hasStartedService: Boolean,
                                val location: TrainLocationDto)

data class CoordinateDto(val latitude: Double,
                         val longitude: Double)


data class TrainLocationDto(val coordinates: CoordinateDto,
                            val mostRecentStation: StationDto?)