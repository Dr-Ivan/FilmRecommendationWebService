package com.filmRecomendationService.webApp.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendationRequestDTO {
    private String userMood;
    private Integer startYear;
    private Integer endYear;
    private String genre;
}
