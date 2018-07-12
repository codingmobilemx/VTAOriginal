package com.vta.codingmobile.vtamovil.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vta.codingmobile.vtamovil.R;

public class ProductsAdapter  extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> implements View.OnClickListener{

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
       public ProductViewHolder(View itemView) {
           super(itemView);
       }
/*  private TextView tvNombreClienteSd;
        private TextView tvDireccionClienteSd;
        private ImageView ivSemaforo;
        private TextView tvTipoSolicitud;
        private ImageView ivPorcentaje;
        private ImageButton ibDetalleSolicitud;

       ProductViewHolder(final View itemView) {
            super(itemView);

            tvNombreClienteSd =     itemView.findViewById(R.id.tvNombreClienteSd);
            tvDireccionClienteSd =  itemView.findViewById(R.id.tvDireccionClienteSd);
            ivSemaforo =            itemView.findViewById(R.id.ivSemaforo);
            ivPorcentaje =          itemView.findViewById(R.id.ivPorcentaje);
            ibDetalleSolicitud =    itemView.findViewById(R.id.ibDetalleSolicitud);
            tvTipoSolicitud =       itemView.findViewById(R.id.tvTipoSolicitud);

            ibDetalleSolicitud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
        }*/
    }
}
