package com.patlaniunam.themoviedb.mvp.topRated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.patlaniunam.themoviedb.R;
import com.patlaniunam.themoviedb.commons.presentation.EndlessScrollListener;
import com.patlaniunam.themoviedb.commons.presentation.OnSingleItemClickListener;
import com.patlaniunam.themoviedb.dto.MovieItemDTO;
import com.patlaniunam.themoviedb.dto.MovieListResponseDTO;
import com.patlaniunam.themoviedb.mvp.detailMovie.DetailMovieActivity;
import com.patlaniunam.themoviedb.mvp.topRated.adapter.TopRatedAdapter;

import java.io.Serializable;
import java.util.List;

import static com.patlaniunam.themoviedb.utils.ViewUtils.isVisible;
import static com.patlaniunam.themoviedb.utils.ViewUtils.setVisibility;

/**
 * Clase que implementa la vista del listado de películas favoritas.
 * @author Luis
 * @version 0.1.1.0
 */
public class TopRatedActivity extends AppCompatActivity implements TopRated.View {

    private TopRated.Presenter presenter;
    private TopRatedAdapter adapter;
    private EndlessScrollListener scrollListener;
    private View loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new TopRatedPresenter(new TopRatedModel(), this);
        loader = findViewById(R.id.loader);
        configRecycler();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("state", new MovieListResponseDTO(scrollListener.getCurrentPage(), adapter.getList()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saved) {
        super.onRestoreInstanceState(saved);
        Serializable extra = saved.getSerializable("state");
        if (extra instanceof MovieListResponseDTO) {
            adapter.addList(((MovieListResponseDTO) extra).getResults());
            scrollListener.setCurrentPage(((MovieListResponseDTO) extra).getPage());
        }
    }

    /** Configura el recycler de la actividad. */
    private void configRecycler() {
        adapter = new TopRatedAdapter(new OnSingleItemClickListener<MovieItemDTO>(250L) {
            @Override
            public void onSingleClick(MovieItemDTO item, int position) {
                startActivity(new Intent(TopRatedActivity.this, DetailMovieActivity.class)
                        .putExtra("item", item));
            }
        });
        scrollListener = new EndlessScrollListener() {
            @Override
            protected boolean isLoading() {
                return isVisible(loader);
            }

            @Override
            public void loadPage(int numPage) {
                presenter.loadMovies(numPage);
            }
        };
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.addOnScrollListener(scrollListener);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter.getItemCount() == 0)
            presenter.loadMovies(1);
    }

    @Override
    public void setLoading(boolean isLoading) {
        setVisibility(findViewById(R.id.loader), isLoading);
    }

    @Override
    public void addMovies(List<MovieItemDTO> movies) {
        adapter.addList(movies);
        scrollListener.setCurrentPage(scrollListener.getCurrentPage() + 1);
    }

    @Override
    public void showError(Throwable throwable) {
        if (adapter.getItemCount() == 0)
            Snackbar.make(loader, R.string.tip_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.label_retry, v -> presenter.loadMovies(1))
            .show();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.destroy();
        if (adapter != null)
            adapter.destroy();
        if (scrollListener != null)
            ((RecyclerView)findViewById(R.id.recycler)).removeOnScrollListener(scrollListener);
        presenter = null;
        adapter = null;
        scrollListener = null;
        loader = null;
        super.onDestroy();
    }
}