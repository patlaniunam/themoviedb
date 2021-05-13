package com.patlaniunam.themoviedb.commons.model;

import androidx.annotation.NonNull;

/**
 * Clase que representa un resultado de una petición.
 * @param <T> Tipo de resultado esperado.
 * @author Luis
 * @version 0.1.1.0
 */
public class Result<T> {

    private final T data;
    private final Throwable error;

    /**
     * Constructor que define un resultado exitoso.
     * @param data Dato que define el resultado exitoso.
     */
    public Result(@NonNull T data) {
        this.data = data;
        this.error = null;
    }

    /**
     * Constructor que define un resultado con error.
     * @param error Error que define el resultado.
     */
    public Result(@NonNull Throwable error) {
        this.data = null;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    /**
     * Indica si el resultado es exitoso.
     * @return Devuelve TRUE si el resultado no tiene un error, FALSE en otro caso.
     */
    public boolean isSuccessfull() {
        return error == null && data != null;
    }
}