package com.example.volcanoes_explorer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.volcanoes_explorer.Models.Volcano;
import com.example.volcanoes_explorer.R;

import java.util.ArrayList;
import java.util.List;

public class VolcanoesAdapter extends RecyclerView.Adapter<VolcanoesAdapter.VolcanoesViewHolder> {
    private List<Volcano> volcanoes;
    private OnVolcanoClickListener listener;
    private List<Volcano> filteredList;
    public VolcanoesAdapter(OnVolcanoClickListener listener) {
        this.listener = listener;
    }

    public void setVolcanoes(List<Volcano> volcanoes) {
        this.volcanoes = volcanoes;
        this.filteredList=volcanoes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VolcanoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.volcano_items, parent, false);
        return new VolcanoesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolcanoesViewHolder holder, int position) {
        Volcano volcano = volcanoes.get(position);
        holder.bind(volcano);
        holder.itemView.setOnClickListener(v -> listener.onVolcanoClick(volcano));
    }

    @Override
    public int getItemCount() {
        return volcanoes != null ? filteredList.size() : 0;
    }

    public interface OnVolcanoClickListener {
        void onVolcanoClick(Volcano volcano);
    }
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if (Key.isEmpty()){
                    filteredList = volcanoes;
                }else {
                    List<Volcano> vcfiltered = new ArrayList<>();
                    for (Volcano row: volcanoes){
                        if (row.getNama().toLowerCase().contains(Key.toLowerCase())){
                            vcfiltered.add(row);
                        }
                    }

                    filteredList=vcfiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values=filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList=(List<Volcano>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public class VolcanoesViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_namaGunung, tv_tinggiMeter;
        private ImageView iv_image;

        public VolcanoesViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_namaGunung = itemView.findViewById(R.id.tv_namaGunung);
            tv_tinggiMeter = itemView.findViewById(R.id.tv_tinggiMeter);
        }

        public void bind(Volcano volcano) {
            Glide.with(itemView.getContext()).load(volcano.getPictUrl()).into(iv_image);
            tv_namaGunung.setText(volcano.getNama());
            tv_tinggiMeter.setText(volcano.getTinggiMeter());
        }
    }
}


