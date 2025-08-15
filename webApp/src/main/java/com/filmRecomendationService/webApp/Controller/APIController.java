package com.filmRecomendationService.webApp.Controller;

import com.filmRecomendationService.webApp.DataTransferObjects.RecommendationRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin
public class APIController {

    @GetMapping("test")
    String testGetFromEndpoint() {
        return "I came to fight for the love of the game, unstoppable!";
    }

    @GetMapping("testJSON")
    String testGetWithArg(@RequestBody RecommendationRequestDTO request) {
        return "Received DTO with mood = " + request.getUserMood() + " start year = " + request.getStartYear() +
                " end year = " + request.getEndYear() + " genre = " + request.getGenre() + ".";
    }
}

// tested with
// curl -X GET -H "Content-Type: Application/json" -d {\"userMood\":\"happy\",\"startYear\":\"1990\",\"endYear\":\"2100\",\"genre\":\"comedy\"}  http://localhost:8080/api/testJSON
