package com.patlaniunam.themoviedb.commons.presentation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static java.util.Objects.requireNonNull;

/**
 * Listener que maneja la paginación de un listado infinito.
 * @author Luis
 * @version 0.1.1.0
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int currentPage = 1;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        RecyclerView.LayoutManager lm = requireNonNull(recyclerView.getLayoutManager());
        LinearLayoutManager layoutManager = (LinearLayoutManager) lm;
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading() && (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + 6))
            loadPage(currentPage);
    }

    /** Indica si se están cargando elementos al listado. */
    protected abstract boolean isLoading();

    /**
     * Carga la siguiente página de elementos.
     * @param numPage Página que se debe cargar.
     */
    public abstract void loadPage(int numPage);
}