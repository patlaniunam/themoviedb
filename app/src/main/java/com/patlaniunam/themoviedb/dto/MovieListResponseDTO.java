package com.patlaniunam.themoviedb.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que define una respuesta de un listado de películas.
 * @author Luis
 * @version 0.1.1.0
 */
public class MovieListResponseDTO implements Serializable {

    private int page;
    private List<MovieItemDTO> results;

    /**
     * Constructor que permite definir los parámetros del listado.
     * @param page Página de la respuesta.
     * @param results Lista de películas.
     */
    public MovieListResponseDTO(int page, List<MovieItemDTO> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public List<MovieItemDTO> getResults() {
        return results;
    }
}