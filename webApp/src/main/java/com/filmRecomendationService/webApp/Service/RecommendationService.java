package com.filmRecomendationService.webApp.Service;

import com.filmRecomendationService.webApp.DataTransferObjects.RecommendationRequestDTO;
import com.filmRecomendationService.webApp.Entity.Film;
import com.filmRecomendationService.webApp.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class RecommendationService {
    @Autowired
    private FilmRepository rep;

    public List<Film> tmp(){
        return rep.selectAllData();
    }

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

    public String makeStringRecommendation(RecommendationRequestDTO request) {
        List<Film> films = handleRecommendationRequest(request);

        if (films.isEmpty()) return "В базе не нашлось фильмов, соответствующих Вашим предпочтениям.";
        StringBuilder recommendation = new StringBuilder("Подборка подходящих фильмов.");
        for (Film film : films) {
            recommendation.append('\n').append('\n');
            recommendation.append("- ").append(film.getTitle()).append(". ");
            recommendation.append("Это фильм в жанре ").append(film.getGenre()
            ).append(", вышедший в ").append(film.getReleaseYear()).append(" году. ");
            recommendation.append("Длительность фильма - ").append(film.getDuration());

            if (film.getDuration() % 10 == 0 || film.getDuration() % 10 == 5 || film.getDuration() % 10 == 6 ||
                    film.getDuration() % 10 == 7 || film.getDuration() % 10 == 8 || film.getDuration() % 10 == 9)
                recommendation.append(" минут. ");
            else if (film.getDuration() % 10 == 1)
                recommendation.append(" минута. ");
            else
                recommendation.append(" минуты. ");

            recommendation.append("Кратко его можно описать так. ").append(film.getDescription());
        }
        return recommendation.toString();
    }

    public String makeStringRecommendation(RecommendationRequestDTO request, String language) {
        if (language.equalsIgnoreCase("ru") || language.equalsIgnoreCase("russian"))
            return makeStringRecommendation(request);

        List<Film> films = handleRecommendationRequest(request);

        if (films.isEmpty()) return "There are no films matching your preferences in the database.";
        StringBuilder recommendation = new StringBuilder("Here are some movies you might like.");
        for (Film film : films) {
            recommendation.append('\n').append('\n');
            recommendation.append("- ").append(film.getTitle()).append(". ");
            recommendation.append("It's a movie in the genre of ").append(film.getGenre()
            ).append(", published in ").append(film.getReleaseYear()).append(". ");
            recommendation.append("Duration of the movie - ").append(film.getDuration());
            recommendation.append(" minutes. ");

            recommendation.append("It can be briefly described as follows. ").append(film.getDescription());
        }
        return recommendation.toString();
    }

}
