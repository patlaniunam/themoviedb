package com.patlaniunam.themoviedb.mvp.detailMovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.patlaniunam.themoviedb.R;
import com.patlaniunam.themoviedb.dto.MovieDetailDTO;
import com.patlaniunam.themoviedb.dto.MovieItemDTO;

import static com.patlaniunam.themoviedb.commons.web.ApiProvider.SIZE_ORIGINAL;
import static com.patlaniunam.themoviedb.utils.ViewUtils.isVisible;
import static com.patlaniunam.themoviedb.utils.ViewUtils.setImage;
import static com.patlaniunam.themoviedb.utils.ViewUtils.setVisibility;
import static com.patlaniunam.themoviedb.utils.ViewUtils.setVote;
import static java.util.Objects.requireNonNull;

/**
 * Clase que implementa el detalle de una actividad.
 * @author Luis
 * @version 0.1.1.0
 */
public class DetailMovieActivity extends AppCompatActivity implements DetailMovie.View {

    private DetailMovie.Presenter presenter;
    private View content;
    private View loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        presenter = new DetailMoviePresenter(new DetailMovieModel(), this);
        content = findViewById(R.id.content);
        loader = findViewById(R.id.loader);
        showMainData(getItem());
        ((Toolbar)findViewById(R.id.toolbar)).setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (getItem() instanceof MovieDetailDTO)
            showDetail((MovieDetailDTO) getItem());
    }

    /**
     * Muestra los datos principales del elemento seleccionado previamente.
     * @param item Elemento seleccionado.
     */
    private void showMainData(MovieItemDTO item) {
        setImage(findViewById(R.id.image), SIZE_ORIGINAL + item.getImage(), loader);
        ((TextView)findViewById(R.id.title)).setText(item.getTitle());
        setVote(findViewById(R.id.vote), item.getVote());
        ((Toolbar)findViewById(R.id.toolbar)).setTitle(item.getTitle());
    }

    /**
     * Obtiene el elemento seleccionado en la lista.
     * @return Devuelve el elemento guardado en el intent de la actividad.
     */
    private MovieItemDTO getItem() {
        return (MovieItemDTO) requireNonNull(getIntent().getSerializableExtra("item"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isVisible(content))
            presenter.loadDetail(getItem().getId());
    }

    @Override
    public void setLoader(boolean isVisible) {
        setVisibility(loader, isVisible);
    }

    @Override
    public void showDetail(MovieDetailDTO detail) {
        showMainData(detail);
        ((TextView)findViewById(R.id.release)).setText(detail.getRelease());
        ((TextView)findViewById(R.id.overview)).setText(detail.getOverview());
        setVisibility(content, true);
        getIntent().putExtra("item", detail);
    }

    @Override
    public void showError(Throwable throwable) {
        Snackbar.make(loader, R.string.tip_error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.label_retry, v -> presenter.loadDetail(getItem().getId()))
                .show();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.destroy();
        content = null;
        loader = null;
        super.onDestroy();
    }
}