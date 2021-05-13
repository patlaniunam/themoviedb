package com.patlaniunam.themoviedb.commons.presentation;

import static java.lang.System.currentTimeMillis;

/**
 * Clase que implementa un listener de click único.
 * @param <T> Tipo de elemento que es seleccionado.
 */
public abstract class OnSingleItemClickListener<T> {

    private long lastClick;
    private long timeWait;

    /**
     * Constructor que define el tiempo de espera entre clicks.
     * @param timeWait Tiempo de espera en milisegundos.
     */
    public OnSingleItemClickListener(long timeWait) {
        this.timeWait = timeWait;
    }

    /**
     * Atiende el click sobre un elemento seleccionado.
     * @param item Elemento seleccionado.
     * @param position Posición del elemento seleccionado.
     */
    public void onItemClick(T item, int position) {
        long currentTime = currentTimeMillis();
        if (currentTime - lastClick > timeWait) {
            lastClick = currentTime;
            onSingleClick(item, position);
        }
    }

    /**
     * Responde a un click único por intervalo de espera.
     * @param item Elemento selecionado al que se le dio click.
     * @param position Posición del elemento al que se le dio click.
     */
    public abstract void onSingleClick(T item, int position);
}