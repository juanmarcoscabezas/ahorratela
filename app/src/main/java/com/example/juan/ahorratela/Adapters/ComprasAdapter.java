package com.example.juan.ahorratela.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.LugaresViewHolder>{
    List<ComprasModel> compraList;
    Context context;
    AhorratelaDB ahorratelaDB;
    int posMinimo;
    float valorMinimo;

    public ComprasAdapter(List<ComprasModel> compraList) {
        this.compraList = compraList;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compras, parent, false);
        LugaresViewHolder lugaresViewHolder = new LugaresViewHolder(v);
        context = v.getContext();
        ahorratelaDB = new AhorratelaDB(v.getContext());
        posMinimo = menorCompra();
        //Vmenorg = menorComparacion();
        //Vmayorg = mayorComparacion();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        ComprasModel compra = compraList.get(position);
        ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());

        holder.idProducto.setText(""+compra.getId_producto());
        holder.idLugar.setText(""+compra.getId_ubicacion());
        holder.nombreProducto.setText(compra.getNombre_producto()+" "+prod.getPresentacion()+ " "+ prod.getMedida() +" " + prod.getUnidad());

        if(posMinimo == position){
            holder.icono.setImageResource(R.drawable.ic_happy);
        }else {
            holder.icono.setImageResource(R.drawable.ic_sad);
        }
        float aux = compra.getValor_compra()/unidad(prod.getUnidad(),prod.getMedida())-valorMinimo;
        holder.valor.setText("Ahorro $ "+aux+" x gramo");
        holder.valor_ahorro.setText(""+aux);
        holder.valor_producto.setText(""+compra.getValor_compra());
    }

    @Override
    public int getItemCount() {
        return compraList.size();
    }

    public class LugaresViewHolder extends RecyclerView.ViewHolder{
        TextView idProducto, idLugar;
        TextView nombreProducto;
        TextView valor;
        TextView valor_producto;
        TextView valor_ahorro;
        ImageView icono;
        TableLayout layoutCompras;
        String fecha;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            idProducto = (TextView) itemView.findViewById(R.id.producto_id);
            idLugar = (TextView) itemView.findViewById(R.id.lugar_id);
            nombreProducto = (TextView) itemView.findViewById(R.id.producto_name);
            valor = (TextView) itemView.findViewById(R.id.producto_value);
            valor_producto = (TextView) itemView.findViewById(R.id.valor_producto);
            valor_ahorro = (TextView) itemView.findViewById(R.id.valor_ahorro);
            icono = (ImageView) itemView.findViewById(R.id.icono);
            layoutCompras = (TableLayout) itemView.findViewById(R.id.layoutCompras);
            fecha = dateFormat.format(date);

            layoutCompras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        if(ahorratelaDB.createCompra(new ComprasModel(Integer.parseInt(idProducto.getText().toString()),Integer.parseInt(idLugar.getText().toString()),fecha,Integer.parseInt(valor_producto.getText().toString()),Float.parseFloat(valor_ahorro.getText().toString())))){
                            Toast.makeText(itemView.getContext(), "Se ha guardado la compra", Toast.LENGTH_SHORT).show();
                            compraList.clear();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(itemView.getContext(), "error al ingresar", Toast.LENGTH_LONG).show();
                        }

                }
            });
        }
    }

    public int menorCompra(){
        int posMinimo = 0;
        if(compraList.size() > 0) {
            ComprasModel compra = compraList.get(0);
            ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());
            valorMinimo = (float) compra.getValor_compra()/unidad(prod.getUnidad(), prod.getMedida());
            for (int i = 0; i < compraList.size(); i++) {
                ComprasModel comprasModel = compraList.get(i);
                prod = ahorratelaDB.getProductoById(comprasModel.getId_producto());
                float valor = (float) comprasModel.getValor_compra()/unidad(prod.getUnidad(),prod.getMedida());
                if ( valor < valorMinimo) {
                    posMinimo = i;
                    valorMinimo = valor;
                }
            }
        }
        return posMinimo;
    }


    private float unidad(String unidad, int medida){
        float gramos = 0;
        if (unidad.toString().equalsIgnoreCase("lb")){
            gramos = (float) 450f*medida;
        }
        if (unidad.toString().equalsIgnoreCase("kg")){
            gramos = (float) 1000*medida;
        }
        if (unidad.toString().equalsIgnoreCase("mg")){
            gramos = (float) (0.001*medida);
        }
        if (unidad.toString().equalsIgnoreCase("gr")){
            gramos = medida;
        }
        if (unidad.toString().equalsIgnoreCase("ml")){
            gramos = medida;
        }
        if (unidad.toString().equalsIgnoreCase("lt")){
            gramos = 1000f*medida;
        }
        return gramos;
    }


}
