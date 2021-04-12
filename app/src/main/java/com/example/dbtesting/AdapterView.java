package com.example.dbtesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class AdapterView extends RecyclerView.Adapter<AdapterView.ProductViewHolder> {

    //accessing the products added to class
    private Context mCtx;
    private List<employees> model;

    public AdapterView(Context mCtx, List<employees> model) {
        this.mCtx = mCtx;
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterView.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = null;
        LayoutInflater mInflater = LayoutInflater.from(mCtx);

        views = mInflater.inflate(R.layout.adapter, parent, false);

        return new ProductViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView.ProductViewHolder holder, int position) {
        employees adsModel = model.get(position);


        holder.Title.setText(adsModel.getName());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView Title;

        public ProductViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title);
        }
    }
}
