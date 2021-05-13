package com.patlaniunam.themoviedb.mvp.detailMovie;

import com.patlaniunam.themoviedb.commons.model.Consumer;
import com.patlaniunam.themoviedb.dto.MovieDetailDTO;

/**
 * Contrato MVP para el detalle de una película.
 * @author Luis
 * @version 0.1.1.0
 */
public interface DetailMovie {

    /** Interfaz que define las acciones del modelo. */
    interface Model {
        /**
         * Obtiene el detalle de una película.
         * @param id Identificador de la película que se consulta.
         * @param consumer Consumidor del detalle de una película.
         */
        void getDetailMovie(long id, Consumer<MovieDetailDTO> consumer);
    }

    /** Interfaz que define las acciones de la vista. */
    interface View {
        /**
         * Ajusta la visibilidad del loader.
         * @param isVisible Indica si es visible el loader.
         */
        void setLoader(boolean isVisible);

        /**
         * Muestra los datos de un detalle.
         * @param detail Datos del detalle de la película.
         */
        void showDetail(MovieDetailDTO detail);

        /** Muestra un error. */
        void showError(Throwable throwable);
    }

    /** Interfaz que define las acciones del presentador. */
    interface Presenter {

        /**
         * Carga el detalle de un identificador.
         * @param id Identificador del detalle.
         */
        void loadDetail(long id);

        /** Limpia las referencias del presentador. */
        void destroy();
    }
}