package ie.markomeara.irelandtraintimes.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

interface TrainPositionsResponse {
    @JacksonXmlRootElement(localName = "ArrayOfObjTrainPositions")
    data class TrainPositionsRootDto(
        @JacksonXmlProperty(localName = "objTrainPositions")
        @JacksonXmlElementWrapper(useWrapping = false)
        val trainPositions: List<TrainPositionDto>
    )

    data class TrainPositionDto(
        @JacksonXmlProperty(localName = "TrainStatus") val status: String,
        @JacksonXmlProperty(localName = "TrainLatitude") val latitude: Double,
        @JacksonXmlProperty(localName = "TrainLongitude") val longitude: Double,
        @JacksonXmlProperty(localName = "TrainCode") val code: String,
        @JacksonXmlProperty(localName = "TrainDate") val date: String, // TODO Some mapping here
        @JacksonXmlProperty(localName = "PublicMessage") val publicMessage: String,
        @JacksonXmlProperty(localName = "Direction") val direction: String
    )
}