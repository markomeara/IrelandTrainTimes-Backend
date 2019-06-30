package ie.markomeara.irelandtraintimes.service;

import ie.markomeara.irelandtraintimes.domain.Coordinate;
import ie.markomeara.irelandtraintimes.domain.Station;
import ie.markomeara.irelandtraintimes.dto.in.StationIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

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
        for (StationIn.StationDto stationDto : stationIn.getStations()) {
            result.add(stationDto.toDomain());
        }
        return result;
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
}
