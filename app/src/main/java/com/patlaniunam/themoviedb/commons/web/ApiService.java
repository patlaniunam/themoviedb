package com.patlaniunam.themoviedb.commons.web;

import com.patlaniunam.themoviedb.dto.MovieDetailDTO;
import com.patlaniunam.themoviedb.dto.MovieListResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Define la lista de servicios disponibles en la API.
 * @author Luis
 * @version 0.1.1.0
 */
public interface ApiService {

    @GET("discover/movie?sort_by=vote_average.desc")
    Call<MovieListResponseDTO> getTopRatedMovies(@Query("api_key") String apiKey,
                                                 @Query("page") int numPage);

    @GET("movie/{movie_id}")
    Call<MovieDetailDTO> getDetailMovie(@Path("movie_id") long id,
                                        @Query("api_key") String apiKey);
}