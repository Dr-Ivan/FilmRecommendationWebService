package com.filmRecomendationService.webApp.Repository;

import com.filmRecomendationService.webApp.Entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(value = "SELECT * FROM movies",
            nativeQuery = true)
    List<Film> selectAllData();

    @Query(value = "SELECT * FROM movies WHERE release_year BETWEEN :start AND :end",
            nativeQuery = true)
    List<Film> selectBetweenYears(@Param("start") Integer earliest, @Param("end") Integer latest);

    @Query(value = "SELECT * " +
            "FROM movies WHERE mood=:user_mood OR secondary_mood=:user_mood " +
            "ORDER BY release_year DESC",
            nativeQuery = true)
    List<Film> selectByMood(@Param("user_mood") String user_mood);

    @Query(value = "SELECT * FROM movies WHERE genre=:user_genre",
            nativeQuery = true)
    List<Film> selectByGenre(@Param("user_genre") String user_genre);


    @Query(value = "SELECT * FROM movies WHERE (release_year BETWEEN :start AND :end) " +
            "AND (mood=:user_mood OR secondary_mood=:user_mood) " +
            "ORDER BY release_year DESC",
            nativeQuery = true)
    List<Film> selectByMoodAndYear(@Param("user_mood") String user_mood,
                                   @Param("start") Integer earliest,
                                   @Param("end") Integer latest);

    @Query(value = "SELECT * FROM movies WHERE (genre=:user_genre) " +
            "AND (mood=:user_mood OR secondary_mood=:user_mood) " +
            "ORDER BY release_year DESC",
            nativeQuery = true)
    List<Film> selectByMoodAndGenre(@Param("user_mood") String user_mood,
                                    @Param("user_genre") String user_genre);

    @Query(value = "SELECT * FROM movies WHERE (release_year BETWEEN :start AND :end) " +
            "AND genre=:user_genre " +
            "AND (mood=:user_mood OR secondary_mood=:user_mood) " +
            "ORDER BY release_year DESC",
            nativeQuery = true)
    List<Film> selectByMoodAndYearAndGenre(@Param("user_mood") String user_mood,
                                           @Param("start") Integer earliest,
                                           @Param("end") Integer latest,
                                           @Param("user_genre") String user_genre);

}
