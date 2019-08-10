package com.example.juan.ahorratela.Activitys;

import android.app.Application;

import com.example.juan.ahorratela.Modelos.LugaresModel;

/**
 * Created by CRISTIAN on 29/09/2018.
 */

public class Global extends Application {
    private LugaresModel lugar;

    public LugaresModel getLugar() {
        return lugar;
    }

    public void setLugar(LugaresModel lugar) {
        this.lugar = lugar;
    }

}
