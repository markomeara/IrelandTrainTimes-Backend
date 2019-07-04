package ie.markomeara.irelandtraintimes.mapper

import ie.markomeara.irelandtraintimes.domain.Coordinate
import ie.markomeara.irelandtraintimes.domain.Station
import ie.markomeara.irelandtraintimes.domain.TrainPositionInfo
import ie.markomeara.irelandtraintimes.dto.out.CoordinateDto
import ie.markomeara.irelandtraintimes.dto.out.StationDto
import ie.markomeara.irelandtraintimes.dto.out.TrainLocationDto
import ie.markomeara.irelandtraintimes.dto.out.TrainPositionInfoDto

@JvmOverloads
fun TrainPositionInfo.toOutboundDto(currentStation: Station? = null): TrainPositionInfoDto {
    val trainLocationDto = TrainLocationDto(this.position.toOutboundDto(), currentStation?.toOutboundDto())
    return TrainPositionInfoDto(this.code, this.status == "R", trainLocationDto)
}

fun Coordinate.toOutboundDto(): CoordinateDto {
    return CoordinateDto(latitude, longitude)
}

fun Station.toOutboundDto(): StationDto {
    return StationDto(code, names.description, location.toOutboundDto())
}