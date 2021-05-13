package com.patlaniunam.themoviedb.mvp.topRated;

import com.patlaniunam.themoviedb.commons.model.Consumer;
import com.patlaniunam.themoviedb.commons.model.Result;
import com.patlaniunam.themoviedb.dto.MovieItemDTO;

import java.util.List;

/**
 * Clase que implementa el presentador de la sección de películas favoritas.
 * @author Luis
 * @version 0.1.1.0
 */
public class TopRatedPresenter implements TopRated.Presenter, Consumer<List<MovieItemDTO>> {

    private TopRated.Model model;
    private TopRated.View view;

    /**
     * Constructor que define los componentes del presentador.
     * @param model Repositorio que usa el presentador para obtener datos.
     * @param view Vista con la que se comunica el presentador.
     */
    public TopRatedPresenter(TopRated.Model model, TopRated.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadMovies(int numPage) {
        view.setLoading(true);
        model.getTopRatedMovies(numPage, this);
    }

    @Override
    public void destroy() {
        view = null;
        model = null;
    }

    @Override
    public void consume(Result<List<MovieItemDTO>> result) {
        if (view != null) {
            view.setLoading(false);
            if (result.isSuccessfull())
                view.addMovies(result.getData());
            else
                view.showError(result.getError());
        }
    }
}