package com.itunes.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Composition {
    private String kind;
    private String artistName;
    private String trackName;
    private Double price;
    private Double trackPrice;
    private String currency;
    private String releaseDate;
}
