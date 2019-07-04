package ie.markomeara.irelandtraintimes.controller;

import ie.markomeara.irelandtraintimes.domain.Station;
import ie.markomeara.irelandtraintimes.dto.out.StationDto;
import ie.markomeara.irelandtraintimes.mapper.DomainToOutboundDtoKt;
import ie.markomeara.irelandtraintimes.service.StationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private StationClient stationClient;

    @Autowired
    public StationController(StationClient stationClient) {
        this.stationClient = stationClient;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StationDto> getMatchingStations(@RequestParam(name = "matchText") String matchText) {
        List<Station> matchingStations = stationClient.getStationsMatchingText(matchText);

        List<StationDto> stationOutResults = new ArrayList<>();

        for (Station matchingStation : matchingStations) {
            StationDto stationDto = DomainToOutboundDtoKt.toOutboundDto(matchingStation);
            stationOutResults.add(stationDto);
        }

        return stationOutResults;
    }

    @GetMapping(path = "/{stationCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StationDto getStationByCode(@PathVariable("stationCode") String stationCode) {
        Station station = stationClient.getStationByCode(stationCode);
        return DomainToOutboundDtoKt.toOutboundDto(station);
    }
}