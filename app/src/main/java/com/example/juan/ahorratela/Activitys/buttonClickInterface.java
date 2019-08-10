package com.example.juan.ahorratela.Activitys;

import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;

/**
 * Created by CRISTIAN on 29/09/2018.
 */

public interface buttonClickInterface {
    public void lugar(LugaresModel lugar);
    public void producto(ProductosModel producto);
    public void compra (ComprasModel compra);
}
