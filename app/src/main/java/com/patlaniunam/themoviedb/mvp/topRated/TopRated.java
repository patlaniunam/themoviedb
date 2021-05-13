package com.patlaniunam.themoviedb.mvp.topRated;

import com.patlaniunam.themoviedb.commons.model.Consumer;
import com.patlaniunam.themoviedb.dto.MovieItemDTO;

import java.util.List;

/**
 * Interfaz que define el módulo del top de películas favoritas.
 * @author Luis
 * @version 0.1.1.0
 */
public interface TopRated {

    /** Interfaz que define las acciones del modelo. */
    interface Model {
        /**
         * Obtiene una página de películas favoritas.
         * @param numPage Número de la página solicitada.
         * @param consumer Consumidor del resultado.
         */
        void getTopRatedMovies(int numPage, Consumer<List<MovieItemDTO>> consumer);
    }

    /** Interfaz que define la vista de la sección. */
    interface View {

        /**
         * Cambia el estado de carga de la vista.
         * @param isLoading Indica si se muestra un loader.
         */
        void setLoading(boolean isLoading);

        /**
         * Muestra una lista de películas.
         * @param movies Lista de películas que se muestran.
         */
        void addMovies(List<MovieItemDTO> movies);

        /**
         * Muestra un error al usuario.
         * @param throwable Causa del error.
         */
        void showError(Throwable throwable);
    }

    /** Interfaz que define el presentador de la sección. */
    interface Presenter {

        /**
         * Se encarga de cargar las películas favoritas.
         * @param numPage Número de página solicitada.
         */
        void loadMovies(int numPage);

        /** Limpia las referencias del presentador. */
        void destroy();
    }
}