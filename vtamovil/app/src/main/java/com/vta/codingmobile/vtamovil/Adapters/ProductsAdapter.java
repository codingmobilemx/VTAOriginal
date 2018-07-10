package com.vta.codingmobile.vtamovil.Adapters;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vta.codingmobile.vtamovil.R;

public class ProductsAdapter  extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> implements View.OnClickListener{

    @Override
    public void onClick(View v) {

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombreClienteSd;
        private TextView tvDireccionClienteSd;
        private ImageView ivSemaforo;
        private TextView tvTipoSolicitud;
        private ImageView ivPorcentaje;
        private ImageButton ibDetalleSolicitud;

        ProductsAdapter(final View itemView) {
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
        }
    }
}
