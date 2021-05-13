package com.patlaniunam.themoviedb.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Clase que representa los datos de interés del una película en una lista.
 * @author Luis
 * @version 0.1.1.0
 */
public class MovieItemDTO implements Serializable {

    private long id;
    private String title;
    @SerializedName("poster_path")
    private String image;
    @SerializedName("vote_average")
    private double vote;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getVote() {
        return vote;
    }
}