package com.example.juan.ahorratela.Adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juan.ahorratela.Activitys.buttonClickInterface;
import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.R;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ComprasProductosAdapter extends RecyclerView.Adapter<ComprasProductosAdapter.LugaresViewHolder>{
    List<ProductosModel> productosList;
    Context context;
    AhorratelaDB lugaresDB;
    FragmentActivity fragmentActivity;
    buttonClickInterface buttonClickInterface;

    public ComprasProductosAdapter(List<ProductosModel> productosList, FragmentActivity fragmentActivity, buttonClickInterface buttonClickInterface) {
        this.productosList = productosList;
        this.fragmentActivity = fragmentActivity;
        this.buttonClickInterface = buttonClickInterface;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compras_productos, parent, false);
        LugaresViewHolder lugaresViewHolder = new LugaresViewHolder(v);
        context = v.getContext();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        ProductosModel productos = productosList.get(position);
        holder.id.setText(productos.getId().toString());
        holder.nombre.setText(productos.getNombre());
        holder.descripcion.setText(productos.getPresentacion()+" "+productos.getMedida()+" "+productos.getUnidad());
        holder.presentacion.setText(productos.getPresentacion().toString());
        holder.unidad.setText(productos.getUnidad().toString());
        holder.medida.setText(productos.getMedida().toString());
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    public class LugaresViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView nombre;
        TextView descripcion;
        TextView presentacion;
        TextView unidad;
        TextView medida;
        RelativeLayout agregarProducto;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idProducto);
            nombre = (TextView) itemView.findViewById(R.id.nombreProducto);
            descripcion = (TextView) itemView.findViewById(R.id.descripcionProducto);
            presentacion = (TextView) itemView.findViewById(R.id.presentacionProducto);
            unidad = (TextView) itemView.findViewById(R.id.unidadProducto);
            medida = (TextView) itemView.findViewById(R.id.medidaProducto);
            agregarProducto = (RelativeLayout) itemView.findViewById(R.id.productosComprasLayout);

            agregarProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClickInterface.producto(new ProductosModel(
                            Integer.parseInt(id.getText().toString()),
                            nombre.getText().toString(),
                            presentacion.getText().toString(),
                            unidad.getText().toString(),
                            Integer.parseInt(medida.getText().toString())
                            ));
                }
            });


        }
    }

}
