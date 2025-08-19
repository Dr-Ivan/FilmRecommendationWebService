package com.filmRecomendationService.webApp.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "movies")
public class Film {
    @Id
    private Long id;

    private String title;
    private String mood;
    private String secondary_mood;
    private String genre;
    private Integer releaseYear;
    private Integer duration;
    private String description;
}
