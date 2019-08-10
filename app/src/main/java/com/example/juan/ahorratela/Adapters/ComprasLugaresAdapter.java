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
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.R;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ComprasLugaresAdapter extends RecyclerView.Adapter<ComprasLugaresAdapter.LugaresViewHolder>{
    List<LugaresModel> lugaresList;
    Context context;
    AhorratelaDB ahorratelaDB;
    FragmentActivity fragmentActivity;
    buttonClickInterface buttonClickInterface;

    public ComprasLugaresAdapter(List<LugaresModel> lugaresList, FragmentActivity fragmentActivity, buttonClickInterface buttonClickInterface) {
        this.lugaresList = lugaresList;
        this.fragmentActivity = fragmentActivity;
        this.buttonClickInterface = buttonClickInterface;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compras_lugares, parent, false);
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

    public class LugaresViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView nombre;
        TextView ubicacion;
        RelativeLayout agregarLugar;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idLugar);
            nombre = (TextView) itemView.findViewById(R.id.nombreLugar);
            ubicacion = (TextView) itemView.findViewById(R.id.ubicacionLugar);
            agregarLugar = (RelativeLayout) itemView.findViewById(R.id.lugaresComprasLayout);

            agregarLugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClickInterface.lugar(new LugaresModel(
                            Integer.parseInt(id.getText().toString()),
                            nombre.getText().toString(),
                            ubicacion.getText().toString()));
                    //((Global) fragmentActivity.getApplication()).setLugar(new LugaresModel(1,nombre.getText().toString(),ubicacion.getText().toString()));
                }
            });


        }
    }

}
