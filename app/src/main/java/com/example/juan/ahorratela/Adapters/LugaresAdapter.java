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
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.R;
import com.example.juan.ahorratela.commons.Validate;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.LugaresViewHolder> {
    List<LugaresModel> lugaresList;
    Context context;
    AhorratelaDB ahorratelaDB;

    public LugaresAdapter(List<LugaresModel> lugaresList) {
        this.lugaresList = lugaresList;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lugares, parent, false);
        LugaresViewHolder lugaresViewHolder = new LugaresViewHolder(v);
        context = v.getContext();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        LugaresModel lugares = lugaresList.get(position);
        holder.id.setText(lugares.getId().toString());
        holder.nombre.setText(lugares.getNombre());
        holder.ubicacion.setText(lugares.getUbicacion());
    }

    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    public class LugaresViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView nombre;
        TextView ubicacion;
        ImageView eliminarLugar;
        Dialog dialog, dialogEdit;
        FloatingActionButton aceptarEliminarLugar;
        FloatingActionButton cancelarEliminarLugar;
        FloatingActionButton aceptarEditarLugarConfirmacion;
        FloatingActionButton cancelarEditarLugarConfirmacion;
        FloatingActionButton editarLugarAceptar;
        FloatingActionButton editarLugarCancelar;
        EditText editTextNombre;
        EditText editTextLugar;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idLugar);
            nombre = (TextView) itemView.findViewById(R.id.nombreLugar);
            ubicacion = (TextView) itemView.findViewById(R.id.ubicacionLugar);
            eliminarLugar = (ImageView) itemView.findViewById(R.id.eliminarLugar);
            dialog = new Dialog(itemView.getContext());
            dialogEdit = new Dialog(itemView.getContext());

            eliminarLugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.setContentView(R.layout.popup_eliminar_lugares_conf);
                    aceptarEliminarLugar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptarEliminarLugar);
                    cancelarEliminarLugar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarEliminarLugar);

                    aceptarEliminarLugar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ahorratelaDB = new AhorratelaDB(itemView.getContext());
                            boolean delete = ahorratelaDB.deleteLugar(id.getText().toString());
                            if (delete) {
                                for (int i = 0; i < lugaresList.size(); i++) {
                                    if (lugaresList.get(i).getId().toString() == id.getText().toString()) {
                                        lugaresList.remove(i);
                                        notifyItemRemoved(i);
                                    }
                                }
                            }
                            dialog.dismiss();
                        }
                    });

                    cancelarEliminarLugar.setOnClickListener(new View.OnClickListener() {
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
                    dialog.setContentView(R.layout.popup_editar_lugares_conf);
                    aceptarEditarLugarConfirmacion = (FloatingActionButton) dialog.findViewById(R.id.btnAceptarEditarLugar);
                    cancelarEditarLugarConfirmacion = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarEditarLugar);

                    aceptarEditarLugarConfirmacion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogEdit.setContentView(R.layout.popup_editar_lugares);
                            editarLugarAceptar = (FloatingActionButton) dialogEdit.findViewById(R.id.btnGuardarLugar);
                            editarLugarCancelar = (FloatingActionButton) dialogEdit.findViewById(R.id.btnCancelarLugar);
                            editTextNombre = (EditText) dialogEdit.findViewById(R.id.editTextNombre1);
                            editTextLugar = (EditText) dialogEdit.findViewById(R.id.editTextUbicacion1);

                            editTextNombre.setText(nombre.getText());
                            editTextLugar.setText(ubicacion.getText().toString());

                            editarLugarAceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        AhorratelaDB ahorratelaDB = new AhorratelaDB(itemView.getContext());
                                        LugaresModel update = ahorratelaDB.updateLugar(id.getText().toString(),
                                                Validate.validarTexto(editTextNombre),
                                                Validate.validarTexto(editTextLugar));
                                        if (update != null) {
                                            for (int i = 0; i < lugaresList.size(); i++) {
                                                if (lugaresList.get(i).getId().toString() == update.getId().toString()) {


                                                    lugaresList.set(i, update);
                                                    notifyDataSetChanged();
                                                }
                                            }
                                        }
                                        dialogEdit.dismiss();
                                    } catch (Exception e) {
                                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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

                    cancelarEditarLugarConfirmacion.setOnClickListener(new View.OnClickListener() {
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
