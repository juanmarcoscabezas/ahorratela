package com.example.juan.ahorratela.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juan.ahorratela.Adapters.ProductosAdapter;
import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.PresentacionesModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.Modelos.UnidadModel;
import com.example.juan.ahorratela.R;
import com.example.juan.ahorratela.commons.Validate;

import java.util.ArrayList;

public class ProductosFragment extends Fragment {

    RecyclerView recyclerView;
    View v;
    FloatingActionButton buttonAdd;
    ArrayList<ProductosModel> productos = new ArrayList<>();
    ArrayList<PresentacionesModel> presentaciones;
    ArrayList<UnidadModel> unidades;
    ProductosAdapter productosAdapter;
    LinearLayoutManager llm;
    Dialog dialog;
    AhorratelaDB ahorratelaDB;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProductosFragment() {
    }

    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_productos, container, false);

        ahorratelaDB = new AhorratelaDB(v.getContext());

        productos = ahorratelaDB.getAllProductos();
        presentaciones = ahorratelaDB.getAllPresentaciones();
        unidades = ahorratelaDB.getAllUnidades();

        buttonAdd = (FloatingActionButton) v.findViewById(R.id.addProducto);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(view);
            }
        });

        dialog = new Dialog(getContext());

        llm = new LinearLayoutManager(v.getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.productos);
        recyclerView.setLayoutManager(llm);

        productosAdapter = new ProductosAdapter(productos);
        recyclerView.setAdapter(productosAdapter);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void ShowPopup(View v) {
        final EditText editTextNombre;
        final EditText editTextMedida;
        FloatingActionButton buttonGuardar;
        FloatingActionButton buttonCancelar;
        final Spinner spinnerPresentaciones;
        final Spinner spinnerUnidades;
        dialog.setContentView(R.layout.popup_registrar_productos);

        editTextNombre = (EditText) dialog.findViewById(R.id.editTextNombrePro);
        editTextMedida = (EditText) dialog.findViewById(R.id.medida);
        buttonGuardar = (FloatingActionButton) dialog.findViewById(R.id.btnGuardarProducto);
        buttonCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarProducto);
        spinnerPresentaciones = (Spinner) dialog.findViewById(R.id.spinnerPresentaciones);
        spinnerUnidades = (Spinner) dialog.findViewById(R.id.spinnerUnidad);

        //AÑADIENDO DATOS AL SPINNER PRESENTACIONES
        final ArrayList<String> arraySpinnerP = new ArrayList<String>();
        for (int i = 0; i < presentaciones.size(); i++) {
            arraySpinnerP.add(presentaciones.get(i).getNombre());
        }
        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(dialog.getContext(), android.R.layout.simple_spinner_item, arraySpinnerP);
        adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPresentaciones.setAdapter(adapterP);

        //AÑADIENDO DATOS AL SPINNER UNIDADES
        final ArrayList<String> arraySpinnerU = new ArrayList<String>();
        for (int i = 0; i < unidades.size(); i++) {
            arraySpinnerU.add(unidades.get(i).getNombre());
        }
        ArrayAdapter<String> adapterU = new ArrayAdapter<String>(dialog.getContext(), android.R.layout.simple_spinner_item, arraySpinnerU);
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnidades.setAdapter(adapterU);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean bool = ahorratelaDB.createProducto(new ProductosModel(
                            1,
                            Validate.validarTexto(editTextNombre),
                            spinnerPresentaciones.getSelectedItem().toString(),
                            spinnerUnidades.getSelectedItem().toString(),
                            Validate.validarNumber(editTextMedida)));
                    if (bool) {
                        productos = ahorratelaDB.getAllProductos();
                        productosAdapter = new ProductosAdapter(productos);
                        recyclerView.setAdapter(productosAdapter);
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}
