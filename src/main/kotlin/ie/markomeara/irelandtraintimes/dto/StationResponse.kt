package ie.markomeara.irelandtraintimes.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

interface StationResponse {
    @JacksonXmlRootElement(localName = "ArrayOfObjStation")
    data class StationRootDto(
        @JacksonXmlProperty(localName = "objStation")
        @JacksonXmlElementWrapper(useWrapping = false)
        val stations: List<StationDto>?
    )

    data class StationDto(
        @JacksonXmlProperty(localName = "StationDesc") val description: String,
        @JacksonXmlProperty(localName = "StationAlias") val alias: String?,
        @JacksonXmlProperty(localName = "StationLatitude") val latitude: String,
        @JacksonXmlProperty(localName = "StationLongitude") val longitude: String,
        @JacksonXmlProperty(localName = "StationCode") val code: String,
        @JacksonXmlProperty(localName = "StationId") val stationId: Int
    )
}