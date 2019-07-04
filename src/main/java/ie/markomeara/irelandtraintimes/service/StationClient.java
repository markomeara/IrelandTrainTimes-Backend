package ie.markomeara.irelandtraintimes.service;

import ie.markomeara.irelandtraintimes.domain.Coordinate;
import ie.markomeara.irelandtraintimes.domain.Station;
import ie.markomeara.irelandtraintimes.dto.in.FilteredStationsIn;
import ie.markomeara.irelandtraintimes.dto.in.StationIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class StationClient {

    private RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public StationClient(RestTemplate restTemplate,
                         @Value("${api.irishrail.baseurl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Cacheable(value = "stations")
    public List<Station> getAllStations() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/getAllStationsXML").build();

        StationIn.StationRootDto stationIn = restTemplate.getForObject(uriComponents.toUriString(), StationIn.StationRootDto.class);
        List<Station> result = new ArrayList<>();
        // TODO Null handling
        for (StationIn.StationDto stationDto : stationIn.getStations()) {
            result.add(stationDto.toDomain());
        }
        return result;
    }

    @Cacheable(value = "stationMatches", key = "#matchText")
    public List<Station> getStationsMatchingText(String matchText) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/getStationsFilterXML")
                .queryParam("StationText", matchText).build();

        FilteredStationsIn.FilteredStationRootDto filteredStationIn = restTemplate.getForObject(uriComponents.toUriString(), FilteredStationsIn.FilteredStationRootDto.class);
        Set<String> filteredStationCodesSet = new LinkedHashSet<>(); // Using LinkedHashSet so no duplicates are added but order is retained
        // TODO Null handling
        for (FilteredStationsIn.FilteredStationDto filteredStation : filteredStationIn.getStations()) {
            filteredStationCodesSet.add(filteredStation.getCode());
        }
        List<Station> matchedStations = new ArrayList<>();

        for (String stationCode : filteredStationCodesSet) {
            Station matchingStation = getStationByCode(stationCode);
            if (matchingStation != null) {
                matchedStations.add(matchingStation);
            }
        }
        return matchedStations;
    }

    @Cacheable(value = "stationCoords", key = "#coordinate")
    public Station getStationByCoordinate(Coordinate coordinate) {
        for (Station station : getAllStations()) {
            if (station.getLocation().equals(coordinate)) {
                return station;
            }
        }
        return null;
    }

    @Cacheable(value = "stationByCode", key = "#code")
    public Station getStationByCode(String code) {
        List<Station> allStations = getAllStations();
        for (Station station : allStations) {
            if (station.getCode().equalsIgnoreCase(code)) {
                return station;
            }
        }
        // TODO Throw exception??
        return null;
    }
}
