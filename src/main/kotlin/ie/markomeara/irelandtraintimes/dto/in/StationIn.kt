package ie.markomeara.irelandtraintimes.dto.`in`

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import ie.markomeara.irelandtraintimes.domain.Coordinate
import ie.markomeara.irelandtraintimes.domain.Station
import ie.markomeara.irelandtraintimes.domain.StationName

interface StationIn {
    @JacksonXmlRootElement(localName = "ArrayOfObjStation")
    data class StationRootDto(
        @JacksonXmlProperty(localName = "objStation")
        @JacksonXmlElementWrapper(useWrapping = false)
        val stations: List<StationDto>?
    )

    data class StationDto(
        @JacksonXmlProperty(localName = "StationDesc") val description: String,
        @JacksonXmlProperty(localName = "StationAlias") val alias: String?,
        @JacksonXmlProperty(localName = "StationLatitude") val latitude: Double,
        @JacksonXmlProperty(localName = "StationLongitude") val longitude: Double,
        @JacksonXmlProperty(localName = "StationCode") val code: String,
        @JacksonXmlProperty(localName = "StationId") val stationId: Int
    ) {
        fun toDomain(): Station {
            return Station(code,
                stationId,
                StationName(description, alias),
                Coordinate(latitude, longitude))
        }
    }
}