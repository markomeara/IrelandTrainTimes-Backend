package ie.markomeara.irelandtraintimes.controller;

import ie.markomeara.irelandtraintimes.TrainPositionNotFoundException;
import ie.markomeara.irelandtraintimes.domain.Station;
import ie.markomeara.irelandtraintimes.domain.TrainPositionInfo;
import ie.markomeara.irelandtraintimes.dto.out.TrainPositionInfoDto;
import ie.markomeara.irelandtraintimes.mapper.DomainToOutboundDtoKt;
import ie.markomeara.irelandtraintimes.service.StationClient;
import ie.markomeara.irelandtraintimes.service.TrainPositionsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {

    private TrainPositionsClient trainPositionsClient;
    private StationClient stationClient;

    @Autowired
    public TrainController(TrainPositionsClient trainPositionsClient,
                           StationClient stationClient) {
        this.trainPositionsClient = trainPositionsClient;
        this.stationClient = stationClient;
    }

    @GetMapping(path = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrainPositionInfoDto> getAll() {
        List<TrainPositionInfoDto> result = new ArrayList<>();
        List<TrainPositionInfo> trainPositionInfos = trainPositionsClient.getCurrentTrainPositions();
        for (TrainPositionInfo trainPositionInfo : trainPositionInfos) {
            TrainPositionInfoDto dto = DomainToOutboundDtoKt.toOutboundDto(trainPositionInfo);
            result.add(dto);
        }
        return result;
    }

    @GetMapping(path = "/{trainCode}/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public TrainPositionInfoDto getLocationOfTrain(@PathVariable("trainCode") String trainCode) {
        TrainPositionInfo trainPositionInfo = trainPositionsClient.getCurrentTrainPosition(trainCode);
        if (trainPositionInfo == null) {
            throw new TrainPositionNotFoundException();
        }
        Station stationMatchingCoordinate = stationClient.getStationByCoordinate(trainPositionInfo.getPosition());
        return DomainToOutboundDtoKt.toOutboundDto(trainPositionInfo, stationMatchingCoordinate);
    }
}
