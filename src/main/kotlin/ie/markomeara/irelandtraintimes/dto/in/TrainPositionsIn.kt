package ie.markomeara.irelandtraintimes.dto.`in`

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import ie.markomeara.irelandtraintimes.domain.Coordinate
import ie.markomeara.irelandtraintimes.domain.TrainPositionInfo

interface TrainPositionsIn {
    @JacksonXmlRootElement(localName = "ArrayOfObjTrainPositions")
    data class TrainPositionsRootDto(
        @JacksonXmlProperty(localName = "objTrainPositions")
        @JacksonXmlElementWrapper(useWrapping = false)
        val trainPositions: List<TrainPositionDto>
    ) {
        fun toTrainPositions(): List<TrainPositionInfo> {
            return trainPositions.map { it.toTrainPosition() }
        }
    }

    data class TrainPositionDto(
        @JacksonXmlProperty(localName = "TrainCode") val code: String,
        @JacksonXmlProperty(localName = "TrainStatus") val status: String,
        @JacksonXmlProperty(localName = "TrainLatitude") val latitude: Double,
        @JacksonXmlProperty(localName = "TrainLongitude") val longitude: Double,
        @JacksonXmlProperty(localName = "PublicMessage") val publicMessage: String,
        @JacksonXmlProperty(localName = "Direction") val direction: String
    ) {
        fun toTrainPosition(): TrainPositionInfo {
            return TrainPositionInfo(code.trim(), status, Coordinate(latitude, longitude), publicMessage, direction)
        }
    }
}