package ie.markomeara.irelandtraintimes.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

interface StationArrivalsDeparturesResponse {
    @JacksonXmlRootElement(localName = "ArrayOfObjStationData")
    data class StationArrivalsDeparturesRootDto (
        val stationDataList: List<TrainPassingDto>
    )

    data class TrainPassingDto(
        val trainCode: String,
        val fullName: String,
        val stationCode: String, // TODO Redundant?
        val trainDate: String,
        val origin: String,
        val destination: String,
        val originTime: String,
        val destinationTime: String,
        val status: String,
        val lastLocation: String?,
        val dueInMinutes: Int,
        val delayMinutes: Int,
        val expectedArrivalTime: String,
        val expectedDepartureTime: String,
        val scheduledArrivalTime: String,
        val scheduledDepartureTime: String,
        val direction: String,
        val trainType: String, // TODO Enum
        val locationType: String // TODO Enum
    )
}