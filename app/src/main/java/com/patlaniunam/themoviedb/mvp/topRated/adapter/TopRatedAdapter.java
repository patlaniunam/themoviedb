package com.patlaniunam.themoviedb.mvp.topRated.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.patlaniunam.themoviedb.R;
import com.patlaniunam.themoviedb.commons.presentation.OnSingleItemClickListener;
import com.patlaniunam.themoviedb.dto.MovieItemDTO;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.patlaniunam.themoviedb.commons.web.ApiProvider.SIZE_500;
import static com.patlaniunam.themoviedb.utils.ViewUtils.setImage;
import static com.patlaniunam.themoviedb.utils.ViewUtils.setVote;

/**
 * Adapter que administra el listado de películas favoritas.
 * @author Luis
 * @version 0.1.1.0
 */
public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.Holder> {

    private List<MovieItemDTO> list;
    private OnSingleItemClickListener<MovieItemDTO> clickListener;

    /** Constructor que inicializa la lista de elementos del adapter. */
    public TopRatedAdapter(@NonNull OnSingleItemClickListener<MovieItemDTO> clickListener) {
        this.list = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public List<MovieItemDTO> getList() {
        return list;
    }

    /**
     * Agrega una lista de elementos al listado.
     * @param list Lista de elementos que se agregan al listado.
     */
    public void addList(List<MovieItemDTO> list) {
        this.list.addAll(list);
        int size = list.size();
        notifyItemRangeInserted(getItemCount() - size, size);
    }

    /** Destruye los componentes internos del adapter. */
    public void destroy() {
        list = null;
        clickListener = null;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_movie, parent, false);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) root.getLayoutParams();
        params.width = parent.getMeasuredWidth() / 3;
        Holder holder = new Holder(root);
        root.setOnClickListener(view -> {
            int position = holder.getBindingAdapterPosition();
            clickListener.onItemClick(list.get(position), position);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MovieItemDTO item = list.get(position);
        setImage(holder.image, SIZE_500 + item.getImage(), holder.loader);
        holder.title.setText(item.getTitle());
        setVote(holder.vote, item.getVote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /** Clase que inyecta los datos de una película en la lista. */
    public static class Holder extends RecyclerView.ViewHolder {

        protected View loader;
        protected ImageView image;
        protected TextView title;
        protected TextView vote;

        /**
         * Constructor que define la vista global con los campos inyectables.
         * @param itemView Vista raíz del holder.
         */
        public Holder(@NonNull View itemView) {
            super(itemView);
            loader = itemView.findViewById(R.id.loader);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            vote = itemView.findViewById(R.id.vote);
        }
    }
}