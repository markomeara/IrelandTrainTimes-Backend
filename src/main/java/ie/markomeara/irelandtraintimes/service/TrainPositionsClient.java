package ie.markomeara.irelandtraintimes.service;

import ie.markomeara.irelandtraintimes.domain.TrainPositionInfo;
import ie.markomeara.irelandtraintimes.dto.in.TrainPositionsIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class TrainPositionsClient {

    private RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public TrainPositionsClient(RestTemplate restTemplate,
                                @Value("${api.irishrail.baseurl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<TrainPositionInfo> getCurrentTrainPositions() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/getCurrentTrainsXML").build();

        return restTemplate.getForObject(uriComponents.toUriString(), TrainPositionsIn.TrainPositionsRootDto.class).toTrainPositions();
    }

    @Nullable
    public TrainPositionInfo getCurrentTrainPosition(String trainCode) {
        List<TrainPositionInfo> trainPositionInfos = getCurrentTrainPositions();
        for (TrainPositionInfo trainPositionInfo : trainPositionInfos) {
            if (trainPositionInfo.getCode().equalsIgnoreCase(trainCode)) {
                return trainPositionInfo;
            }
        }
        return null;
    }
}
