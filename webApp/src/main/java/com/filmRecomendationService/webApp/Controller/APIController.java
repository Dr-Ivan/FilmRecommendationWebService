package com.filmRecomendationService.webApp.Controller;

import com.filmRecomendationService.webApp.DataTransferObjects.RecommendationRequestDTO;
import com.filmRecomendationService.webApp.Entity.Film;
import com.filmRecomendationService.webApp.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/", produces = "text/plain;charset=UTF-8")
@CrossOrigin
public class APIController {
    @Autowired
    private RecommendationService model;

    @GetMapping("recommendationList")
    List<Film> getRecommendationList(@RequestBody RecommendationRequestDTO request) {
        return model.handleRecommendationRequest(request);
    }

    @GetMapping("recommendationString")
    String getRecommendationString(@RequestBody RecommendationRequestDTO request) {
        return model.makeStringRecommendation(request, "en");
    }

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
