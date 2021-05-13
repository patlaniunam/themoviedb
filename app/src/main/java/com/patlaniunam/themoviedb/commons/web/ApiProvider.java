package com.patlaniunam.themoviedb.commons.web;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase que define los servicios web con Retrofit.
 * @author Luis
 * @version 0.1.1.0
 */
public class ApiProvider {

    /** Ruta base de las peticiones. */
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    /** Ruta base de las imágenes de las películas. */
    public static String BASE_URL_IMAGES = "http://image.tmdb.org/t/p/";
    /** Tamaño original de la imagen. */
    public static String SIZE_ORIGINAL = "original";
    /** Tamaño mediano de la imagen. */
    public static String SIZE_500 = "w500";

    private static Retrofit instance;

    /**
     * Obtiene la instancia única de retrofit en la app.
     * @param <T> Tipo de servicio que se solicita como cliente.
     * @return Devuelve la instancia estática de Retorfit.
     */
    public static <T> T provide(Class<T> apiService) {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance.create(apiService);
    }
}