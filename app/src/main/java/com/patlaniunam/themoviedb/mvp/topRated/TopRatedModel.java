package com.patlaniunam.themoviedb.mvp.topRated;

import com.patlaniunam.themoviedb.commons.model.Consumer;
import com.patlaniunam.themoviedb.commons.model.Result;
import com.patlaniunam.themoviedb.commons.web.ApiService;
import com.patlaniunam.themoviedb.dto.MovieItemDTO;
import com.patlaniunam.themoviedb.dto.MovieListResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.patlaniunam.themoviedb.BuildConfig.API_KEY;
import static com.patlaniunam.themoviedb.commons.web.ApiProvider.provide;

/**
 * Clase que implementa las peticiones asociadas a las películas.
 * @author Luis
 * @version 0.1.1.0
 */
public class TopRatedModel implements TopRated.Model {

    /**
     * Obtiene el top de películas favoritas.
     * @param numPage Número de página solicitada.
     * @param consumer Consume el resultado obtenido.
     */
    public void getTopRatedMovies(int numPage, Consumer<List<MovieItemDTO>> consumer) {
        provide(ApiService.class).getTopRatedMovies(API_KEY, numPage)
        .enqueue(new Callback<MovieListResponseDTO>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<MovieListResponseDTO> call,
                                   Response<MovieListResponseDTO> response) {
                MovieListResponseDTO movies = response.body();
                if (movies != null)
                    consumer.consume(new Result<>(movies.getResults()));
                else
                    onFailure(call, new NullPointerException());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<MovieListResponseDTO> call, Throwable t) {
                consumer.consume(new Result<>(t));
            }
        });
    }
}