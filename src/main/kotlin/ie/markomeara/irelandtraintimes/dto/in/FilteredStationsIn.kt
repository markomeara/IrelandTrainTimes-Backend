package ie.markomeara.irelandtraintimes.dto.`in`

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

interface FilteredStationsIn {
    @JacksonXmlRootElement(localName = "ArrayOfObjStationFilter")
    data class FilteredStationRootDto(
        @JacksonXmlProperty(localName = "objStationFilter")
        @JacksonXmlElementWrapper(useWrapping = false)
        val stations: List<FilteredStationDto>?
    )

    data class FilteredStationDto(
        @JacksonXmlProperty(localName = "StationDesc_sp") val description_sp: String,
        @JacksonXmlProperty(localName = "StationCode") val code: String,
        @JacksonXmlProperty(localName = "StationId") val stationId: Int)
}