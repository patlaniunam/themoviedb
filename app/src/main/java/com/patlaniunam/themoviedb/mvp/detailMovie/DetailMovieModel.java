package com.patlaniunam.themoviedb.mvp.detailMovie;

import com.patlaniunam.themoviedb.commons.model.Consumer;
import com.patlaniunam.themoviedb.commons.model.Result;
import com.patlaniunam.themoviedb.commons.web.ApiService;
import com.patlaniunam.themoviedb.dto.MovieDetailDTO;

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
public class DetailMovieModel implements DetailMovie.Model {

    /**
     * Obtiene el detalle de la película.
     * @param id Identificador del detalle de una película.
     * @param consumer Consume el resultado obtenido.
     */
    public void getDetailMovie(long id, Consumer<MovieDetailDTO> consumer) {
        provide(ApiService.class).getDetailMovie(id, API_KEY)
        .enqueue(new Callback<MovieDetailDTO>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<MovieDetailDTO> call, Response<MovieDetailDTO> response) {
                MovieDetailDTO detail = response.body();
                if (detail != null)
                    consumer.consume(new Result<>(detail));
                else
                    onFailure(call, new NullPointerException());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<MovieDetailDTO> call, Throwable t) {
                consumer.consume(new Result<>(t));
            }
        });
    }
}