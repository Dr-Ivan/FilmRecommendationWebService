package com.filmRecomendationService.webApp.Controller;

import com.filmRecomendationService.webApp.DataTransferObjects.RecommendationRequestDTO;
import com.filmRecomendationService.webApp.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/", produces = "text/plain;charset=UTF-8")
@CrossOrigin//(origins = "http://127.0.0.1:5500")
public class APIController {
    @Autowired
    private RecommendationService model;


    @GetMapping("recommendationString")
    String getRecommendationString(
            @RequestParam String userMood,
            @RequestParam Integer startYear,
            @RequestParam Integer endYear,
            @RequestParam String genre
    ) {
        RecommendationRequestDTO request = new RecommendationRequestDTO(userMood,
                startYear,
                endYear,
                genre);
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
