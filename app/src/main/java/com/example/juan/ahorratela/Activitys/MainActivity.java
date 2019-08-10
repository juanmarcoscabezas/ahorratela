package com.example.juan.ahorratela.Activitys;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Fragments.ComprasFragment;
import com.example.juan.ahorratela.Fragments.HistorialesFragment;
import com.example.juan.ahorratela.Fragments.LugaresFragment;
import com.example.juan.ahorratela.Fragments.ProductosFragment;
import com.example.juan.ahorratela.R;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    LugaresFragment lugaresFragment = new LugaresFragment();
    ComprasFragment comprasFragment = new ComprasFragment();
    ProductosFragment productosFragment = new ProductosFragment();
    HistorialesFragment historialesFragment = new HistorialesFragment();
    android.support.v7.widget.Toolbar toolbar;
    AhorratelaDB ahorratelaDB;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_compras:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, comprasFragment).commit();
                    toolbar.setTitle("Ahorratela - comparar");
                    return true;
                case R.id.navigation_registros:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, productosFragment).commit();
                    toolbar.setTitle("Ahorratela - productos");
                    return true;
                case R.id.navigation_graficas:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, historialesFragment).commit();
                    toolbar.setTitle("Ahorratela - graficos");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, comprasFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ahorratelaDB = new AhorratelaDB(getApplicationContext());

        ahorratelaDB.deleteAllUnidades();
        ahorratelaDB.deleteAllPresentaciones();

        ahorratelaDB.createPresentacion("Bolsa");
        ahorratelaDB.createPresentacion("Botella");
        ahorratelaDB.createPresentacion("Paquete");

        ahorratelaDB.createUnidad("kg");
        ahorratelaDB.createUnidad("gr");
        ahorratelaDB.createUnidad("mg");
        ahorratelaDB.createUnidad("lb");
        ahorratelaDB.createUnidad("ml");
        ahorratelaDB.createUnidad("lt");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        toolbar.setTitle("Ahorratela - comparar");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_productos) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, productosFragment).commit();
            toolbar.setTitle("Ahorratela - productos");
            return true;
        }
        if (id == R.id.action_lugares) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, lugaresFragment).commit();
            toolbar.setTitle("Ahorratela - lugares");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
