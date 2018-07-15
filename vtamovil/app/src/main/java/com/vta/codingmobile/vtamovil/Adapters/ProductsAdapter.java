package com.vta.codingmobile.vtamovil.Adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vta.codingmobile.vtamovil.Helpers.Images.DownloadImage;
import com.vta.codingmobile.vtamovil.Model.Clases.Product;
import com.vta.codingmobile.vtamovil.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    private List<Product> products = new ArrayList<>();

    public ProductsAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(itemView);
        itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = products.get(position);
        DownloadImage downloadImage = new DownloadImage(holder.imvProduct);
        downloadImage.execute(product.getImages().getUrl());

    }

    @Override
    public void onClick(View v) {
        if (this.listener != null) {
            this.listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView imvProduct;

        ProductViewHolder(final View itemView) {
            super(itemView);

            imvProduct = itemView.findViewById(R.id.imvProduct);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
