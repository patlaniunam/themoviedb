package com.patlaniunam.themoviedb.mvp.detailMovie;

import com.patlaniunam.themoviedb.commons.model.Consumer;
import com.patlaniunam.themoviedb.commons.model.Result;
import com.patlaniunam.themoviedb.dto.MovieDetailDTO;

/**
 * Clase que implementa el presentador del detalle.
 * @author Luis
 * @version 0.1.1.0
 */
public class DetailMoviePresenter implements DetailMovie.Presenter, Consumer<MovieDetailDTO> {

    private DetailMovie.Model model;
    private DetailMovie.View view;

    /**
     * Constructor que define el modelo y la vista con que se comunica el presentador.
     * @param model Modelo que maneja el presentador.
     * @param view Vista que maneja el presentador.
     */
    public DetailMoviePresenter(DetailMovie.Model model, DetailMovie.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadDetail(long id) {
        view.setLoader(true);
        model.getDetailMovie(id, this);
    }

    @Override
    public void destroy() {
        view = null;
        model = null;
    }

    @Override
    public void consume(Result<MovieDetailDTO> result) {
        if (view != null) {
            view.setLoader(false);
            if (result.isSuccessfull())
                view.showDetail(result.getData());
            else
                view.showError(result.getError());
        }
    }
}