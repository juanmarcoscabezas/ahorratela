package com.example.juan.ahorratela.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.R;
import com.example.juan.ahorratela.commons.Validate;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder>{
    List<ProductosModel> productosModels;
    Context context;
    AhorratelaDB productosDB;

    public ProductosAdapter(List<ProductosModel> productosModels) {
        this.productosModels = productosModels;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_productos, parent, false);
        ProductosViewHolder lugaresViewHolder = new ProductosViewHolder(v);
        context = v.getContext();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position) {
        ProductosModel productos = productosModels.get(position);
        holder.id.setText(productos.getId().toString());
        holder.nombre.setText(productos.getNombre());
        holder.descripcion.setText(productos.getPresentacion()+" "+productos.getMedida()+" "+productos.getUnidad());
    }

    @Override
    public int getItemCount() {
        return productosModels.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView nombre;
        TextView descripcion;
        ImageView eliminarProducto;
        Dialog dialog, dialogEdit;
        FloatingActionButton aceptarEliminarProducto;
        FloatingActionButton cancelarEliminarProducto;
        FloatingActionButton aceptarEditarProductoConfirmacion;
        FloatingActionButton cancelarEditarProductoConfirmacion;
        FloatingActionButton editarLugarAceptar;
        FloatingActionButton editarLugarCancelar;
        EditText editTextNombre;

        public ProductosViewHolder(final View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idProducto);
            nombre = (TextView) itemView.findViewById(R.id.nombreProducto);
            descripcion = (TextView) itemView.findViewById(R.id.descripcionProducto);
            eliminarProducto = (ImageView) itemView.findViewById(R.id.eliminarProducto);
            dialog = new Dialog(itemView.getContext());
            dialogEdit = new Dialog(itemView.getContext());

            eliminarProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.setContentView(R.layout.popup_eliminar_productos_conf);
                    aceptarEliminarProducto = (FloatingActionButton) dialog.findViewById(R.id.btnAceptarEliminarProducto);
                    cancelarEliminarProducto = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarEliminarProducto);

                    aceptarEliminarProducto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            productosDB = new AhorratelaDB(itemView.getContext());
                            boolean delete = productosDB.deleteProducto(id.getText().toString());
                            if (delete){
                                for (int i = 0; i< productosModels.size(); i++){
                                    if(productosModels.get(i).getId().toString() == id.getText().toString()){
                                        productosModels.remove(i);
                                        notifyItemRemoved(i);
                                    }
                                }
                            }
                            dialog.dismiss();
                        }
                    });

                    cancelarEliminarProducto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.setContentView(R.layout.popup_editar_productos_conf);
                    aceptarEditarProductoConfirmacion = (FloatingActionButton) dialog.findViewById(R.id.btnAceptarEditarProducto);
                    cancelarEditarProductoConfirmacion = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarEditarProducto);

                    aceptarEditarProductoConfirmacion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogEdit.setContentView(R.layout.popup_editar_productos);
                            editarLugarAceptar = (FloatingActionButton) dialogEdit.findViewById(R.id.btnGuardarProducto);
                            editarLugarCancelar = (FloatingActionButton) dialogEdit.findViewById(R.id.btnCancelarProducto);
                            editTextNombre = (EditText) dialogEdit.findViewById(R.id.editTextNombre1);

                            editTextNombre.setText(nombre.getText());

                            editarLugarAceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        productosDB = new AhorratelaDB(itemView.getContext());
                                        ProductosModel update = productosDB.updateProducto(id.getText().toString(),
                                                Validate.validarTexto(editTextNombre));
                                        if(update != null){
                                            for (int i = 0; i < productosModels.size(); i++) {
                                                if (productosModels.get(i).getId().toString() == update.getId().toString()) {
                                                    update.setPresentacion(productosModels.get(i).getPresentacion());
                                                    update.setUnidad(productosModels.get(i).getPresentacion());
                                                    update.setMedida(productosModels.get(i).getMedida());
                                                    productosModels.set(i, update);
                                                    notifyItemChanged(i);
                                                }
                                            }
                                            dialogEdit.dismiss();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(itemView.getContext(),e.getMessage(),Toast.LENGTH_LONG);
                                    }
                                }
                            });

                            editarLugarCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogEdit.dismiss();
                                }
                            });
                            dialog.dismiss();
                            dialogEdit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialogEdit.show();
                        }
                    });

                    cancelarEditarProductoConfirmacion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
        }
    }
}
