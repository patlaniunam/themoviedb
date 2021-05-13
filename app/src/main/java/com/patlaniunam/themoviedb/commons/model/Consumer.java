package com.patlaniunam.themoviedb.commons.model;

/**
 * Interfaz que permite definir el consumo de un recurso.
 * @param <T> Tipo de recurso que se consume.
 * @author Luis
 * @version 0.1.1.0
 */
public interface Consumer<T> {

    /**
     * Recibe un resultado con un consumo.
     * @param result Resultado recibido que puede o no tener un recurso.
     */
    void consume(Result<T> result);
}