package ie.markomeara.irelandtraintimes.controller;

import ie.markomeara.irelandtraintimes.dto.TrainPositionsResponse.TrainPositionsRootDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TrainController {

    @RequestMapping("/")
    public String index() {
        RestTemplate restTemplate = new RestTemplate();
        TrainPositionsRootDto data = restTemplate.getForObject("https://api.irishrail.ie/realtime/realtime.asmx/getCurrentTrainsXML", TrainPositionsRootDto.class);
        System.out.println(data);
        return "success";
    }
}
