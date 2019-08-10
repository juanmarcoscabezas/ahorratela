package com.example.juan.ahorratela.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.juan.ahorratela.ChartAdapter;
import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Graficos;
import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.Modelos.GraficaColumna;
import com.example.juan.ahorratela.Modelos.GraficaLineas;
import com.example.juan.ahorratela.Modelos.GraficaPastel;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistorialesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistorialesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistorialesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Context context;
    ArrayList<GraficaLineas> graficaLineas;
    ArrayList<GraficaPastel> graficaPastels;
    ArrayList<GraficaColumna> graficaColumnas;
    AhorratelaDB ahorratelaBD;
    private PieChartView chart;
    private PieChartView pieChartView;
    private PieChartData data;
    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = true;
    private boolean hasLabelForSelected = false;
    private ListView listView;
    private ChartAdapter adapter;

    public HistorialesFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistorialesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistorialesFragment newInstance(String param1, String param2) {
        HistorialesFragment fragment = new HistorialesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_historiales, container, false);
        context = v.getContext();
        
        ahorratelaBD = new AhorratelaDB(context);

        graficaLineas=ahorratelaBD.getGraficaLineas();
        graficaPastels=ahorratelaBD.getcantidadProductos();
        graficaColumnas=ahorratelaBD.getGraficaAhorroLugar();


        listView = (ListView) v.findViewById(android.R.id.list);

        adapter = new ChartAdapter(context, 0, crearGraficas());
        listView.setAdapter(adapter);

        return v;
    }

    public List<Graficos> crearGraficas(){
        List<Graficos> lt = new ArrayList<Graficos>();


        lt.add(new Graficos("Grafico Redtas compra vs ahorro",1, context,graficaLineas));
        lt.add(new Graficos(context,2,"Grafico Pastel ahorro",graficaPastels));
        lt.add(new Graficos(context,"Grafico Barras perdidas por lugar",graficaColumnas,3));

        return lt;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }

    }
}
