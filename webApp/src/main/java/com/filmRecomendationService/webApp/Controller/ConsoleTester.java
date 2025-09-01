package com.filmRecomendationService.webApp.Controller;

import com.filmRecomendationService.webApp.DataTransferObjects.RecommendationRequestDTO;
import com.filmRecomendationService.webApp.Entity.Film;
import com.filmRecomendationService.webApp.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class ConsoleTester implements CommandLineRunner {
    @Autowired
    private RecommendationService testObject;

    @Override
    public void run(String... args) {
        System.out.println("TEST");
        RecommendationRequestDTO testRequest = new RecommendationRequestDTO(
                "nostalgic",
                1900,
                2100,
                "любой");

        Film film = testObject.handleRecommendationRequest(testRequest).get(0);
        System.out.println(testObject.handleRecommendationRequest(testRequest));
        System.out.println(testObject.makeStringRecommendation(testRequest, "en"));

        testRequest.setGenre("комедия");
        testObject.handleRecommendationRequest(testRequest);

        testRequest.setGenre("любой");
        testRequest.setStartYear(1990);
        testRequest.setEndYear(2005);
        testObject.handleRecommendationRequest(testRequest);


        testRequest.setGenre("комедия");
        testObject.handleRecommendationRequest(testRequest);


        System.out.println("file.encoding: " + System.getProperty("file.encoding"));
        System.out.println("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
        System.out.println("Default Charset: " + Charset.defaultCharset());
    }
}
