package com.filmRecomendationService.webApp.Service;

import com.filmRecomendationService.webApp.DataTransferObjects.RecommendationRequestDTO;
import com.filmRecomendationService.webApp.Entity.Film;
import com.filmRecomendationService.webApp.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    @Autowired
    private FilmRepository rep;

    public List<Film> handleRecommendationRequest(RecommendationRequestDTO request) {
        boolean needYear = request.getStartYear() != 1900 || request.getEndYear() != 2100;
        boolean needGenre = !request.getGenre().equals("любой");
        if (!needYear && !needGenre) {
            System.out.println("mood only");
            return rep.selectByMood(request.getUserMood());
        } else if (!needYear) {
            System.out.println("mood and genre");
            return rep.selectByMoodAndGenre(
                    request.getUserMood(),
                    request.getGenre());
        } else if (!needGenre) {
            System.out.println("mood and year");
            return rep.selectByMoodAndYear(
                    request.getUserMood(),
                    request.getStartYear(),
                    request.getEndYear());
        } else {
            System.out.println("ALL params");
            return rep.selectByMoodAndYearAndGenre(
                    request.getUserMood(),
                    request.getStartYear(),
                    request.getEndYear(),
                    request.getGenre());
        }
    }
}
