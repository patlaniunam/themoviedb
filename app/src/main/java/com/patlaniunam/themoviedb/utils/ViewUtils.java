package com.patlaniunam.themoviedb.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.Nullable;

import static com.patlaniunam.themoviedb.commons.web.ApiProvider.BASE_URL_IMAGES;

/**
 * Clase que define funciones facilitan la inyección de datos y manejo de vistas.
 * @author Luis
 * @version 0.1.1.0
 */
@SuppressLint("DefaultLocale")
public class ViewUtils {

    /**
     * Carga una imagen a partir de una ruta final de imagen.
     * @param image Campo en el que se muestra la imagen.
     * @param path Ruta final de la imagen.
     * @param loader Vista que se muestra durante la carga de la imagen.
     */
    public static void setImage(ImageView image, String path, View loader) {
        if (loader != null)
            loader.setVisibility(View.VISIBLE);
        Glide.with(image.getContext())
                .load(BASE_URL_IMAGES + path)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (loader != null)
                            loader.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (loader != null)
                            loader.setVisibility(View.GONE);
                        image.animate().alpha(1f);
                        return false;
                    }
                }).into(image);
    }

    /**
     * Muestra una votación en un campo de texto, con el formato correcto.
     * @param text Campo en el que se muestra la calificación.
     * @param vote Valor de la califcación sin formato.
     */
    public static void setVote(TextView text, double vote) {
        String formatted = String.format("%.1f", vote);
        text.setText(formatted);
    }

    /**
     * Indica si una vista es actualmente visible.
     * @param view Vista que es verificada.
     * @return Devuelve TRUE si la vista es actualmente visible, FALSE en otro caso.
     */
    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * Ajusta la visibilidad de una vista a partir de un valor booleano.
     * @param view Vista a la que se ajusta la visibilidad.
     * @param visible Indica con TRUE si la vista es visible y FALSE en otro caso.
     */
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible? View.VISIBLE: View.GONE);
    }
}