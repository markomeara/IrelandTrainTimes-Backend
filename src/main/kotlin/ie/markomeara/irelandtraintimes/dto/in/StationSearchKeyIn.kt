package ie.markomeara.irelandtraintimes.dto.`in`

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

interface StationSearchKeyIn {
    @JacksonXmlRootElement(localName = "ArrayOfObjStationFilter")
    data class StationSearchKeyRootDto(
        @JacksonXmlProperty(localName = "objTrainPositions")
        @JacksonXmlElementWrapper(useWrapping = false)
        val stationKeyList: List<TrainPositionsIn.TrainPositionDto>
    )

    data class StationKeyDto(
        @JacksonXmlProperty(localName = "StationDesc") val stationDescription: String,
        @JacksonXmlProperty(localName = "StationCode") val stationCode: String
    )
}