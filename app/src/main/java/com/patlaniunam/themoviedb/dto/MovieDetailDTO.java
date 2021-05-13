package com.patlaniunam.themoviedb.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Clase que representa los datos de interés del detalle de una película.
 * @author Luis
 * @version 0.1.1.0
 */
public class MovieDetailDTO extends MovieItemDTO {

    private String overview;
    @SerializedName("release_date")
    private String release;

    public String getOverview() {
        return overview;
    }

    public String getRelease() {
        return release;
    }
}