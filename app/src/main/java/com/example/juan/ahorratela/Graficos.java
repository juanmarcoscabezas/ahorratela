package com.example.juan.ahorratela;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import android.content.Context;
import android.graphics.Color;

import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.Modelos.GraficaColumna;
import com.example.juan.ahorratela.Modelos.GraficaLineas;
import com.example.juan.ahorratela.Modelos.GraficaPastel;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Graficos {
    String titulo;
    ArrayList<GraficaLineas> graficaLineas;
    ArrayList<GraficaPastel> graficaPastel;
    ArrayList<GraficaColumna> graficaColumnas;

    Object grafica;
    private PieChartData data;
    private ColumnChartData columnChartData;
    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = true;
    private boolean hasLabelForSelected = false;
    int tipo;

    public Graficos(String tit,int tipo, Context c,ArrayList<GraficaLineas> graficaLineas) {
        this.graficaLineas=graficaLineas;
        titulo = tit;
        this.tipo = tipo;
        if (tipo == 1) {
            cargarGraficoLinea(c);
        }
    }

    public Graficos(Context context,int tipo,String tit, ArrayList<GraficaPastel> graficaPastels) {
        this.titulo = tit;
        this.graficaPastel = graficaPastels;
        this.tipo=tipo;
        if(tipo==2){
            cargarGraficoPieChartView(context);
        }
    }

    public Graficos(Context context, String titulo, ArrayList<GraficaColumna> graficaColumnas, int tipo) {
        this.titulo = titulo;
        this.graficaColumnas = graficaColumnas;
        this.tipo = tipo;
        if(tipo==3){
            cargarGraficoColumChartView(context);
        }
    }

    public void cargarGraficoLinea(Context c) {
        LineChartView chart2 = new LineChartView(c);
        List<PointValue> values = new ArrayList<PointValue>();
        Line line;
        List<Line> lines = new ArrayList<Line>();

        values=new ArrayList<PointValue>();
        for(int i=0;i<graficaLineas.size();i++){
            values.add(new PointValue(i, (float) graficaLineas.get(i).getAhorro()).setLabel(("producto: "+graficaLineas.get(i).getNombre()+" ahorro:"+graficaLineas.get(i).getAhorro()).toString()));

        }
        line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setAreaTransparency(255);
        line.setFilled(false);
        line.setPointRadius(1);
        line.setHasLabels(true);
        lines.add(line);

        values=new ArrayList<PointValue>();
        for(int i=0;i<graficaLineas.size();i++){
            values.add(new PointValue(i,(graficaLineas.get(i).getValor())).setLabel(("producto : "+graficaLineas.get(i).getNombre()+" compra:"+graficaLineas.get(i).getValor()).toString()));
        }
        line = new Line(values);
        line.setColor(ChartUtils.COLOR_RED);
        line.setAreaTransparency(255);
        line.setFilled(false);
        line.setPointRadius(1);
        line.setHasLabels(true);
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        chart2.setLineChartData(data);
        grafica = chart2;
    }

    public void cargarGraficoPieChartView(Context c){
        PieChartView chart2=new PieChartView(c);

        List<SliceValue> values = new ArrayList<SliceValue>();

        for (int i = 0; i < graficaPastel.size(); ++i) {
            SliceValue sliceValue = new SliceValue(graficaPastel.get(i).getCantidad(), Color.rgb((int)(Math.random() * 256 + 1),(int)(Math.random() * 256 + 1),(int)(Math.random() * 256 + 1)));
            values.add(sliceValue.setLabel((("nombre: "+graficaPastel.get(i).getNombre()+" cantidad: "+graficaPastel.get(i).getCantidad())).toString()));
        }
        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);
        hasCenterText1 = true;
        hasCenterCircle = true;
        hasLabels = true;
        chart2.setPieChartData(data);
        chart2.setCircleFillRatio(0.9f);
        chart2.setValueSelectionEnabled(hasLabelForSelected);
        data.setCenterText1("productos");
        data.setCenterText1FontSize(ChartUtils.px2sp(chart2.getResources().getDisplayMetrics().scaledDensity,
                (int) chart2.getResources().getDimension(R.dimen.compra)));
        grafica=chart2;
    }

    public void cargarGraficoColumChartView(Context c){
        ColumnChartView chart2 = new ColumnChartView(c);
        int numSubcolumns = 1;
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < graficaColumnas.size(); ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) graficaColumnas.get(i).getAhorro(), Color.rgb((int)(Math.random() * 256 + 1),(int)(Math.random() * 256 + 1),(int)(Math.random() * 256 + 1))).setLabel("lugar: "+graficaColumnas.get(i).getNombre()+" ahorro: "+graficaColumnas.get(i).getAhorro()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }
        columnChartData=new ColumnChartData(columns);
        chart2.setColumnChartData(columnChartData);
        grafica=chart2;
    }

    public void cargarGraficoTempoChartView(Context c){
        LineChartView chart2 = new LineChartView(c);
        float minHeight = 200;
        float maxHeight = 300;
        float tempoRange = 10; // from 0min/km to 15min/km

        float scale = tempoRange / maxHeight;
        float sub = (minHeight * scale) / 2;

        int numValues = 20;

        Line line;
        List<PointValue> values;
        List<Line> lines = new ArrayList<Line>();

        // Height line, add it as first line to be drawn in the background.
        values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            // Some random height values, add +200 to make line a little more natural
            float rawHeight = (float) (Math.random() * 100 + 200);
            float normalizedHeight = rawHeight * scale - sub;
            values.add(new PointValue(i, normalizedHeight).setLabel(""+(float) (Math.random() * 100 + 200)));
        }

        line = new Line(values);
        line.setColor(Color.GREEN);
        line.setHasPoints(false);
        line.setFilled(true);
        line.setStrokeWidth(1);
        lines.add(line);

        // Tempo line is a little tricky because worse tempo means bigger value for example 11min per km is worse
        // than 2min per km but the second should be higher on the chart. So you need to know max tempo and
        // tempoRange and set
        // chart values to minTempo - realTempo.
        values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            // Some random raw tempo values.
            float realTempo = (float) Math.random() * 6 + 2;
            float revertedTempo = tempoRange - realTempo;
            values.add(new PointValue(i, revertedTempo).setLabel("la 2"+(float) Math.random() * 6 + 2));
        }

        line = new Line(values);
        line.setColor(ChartUtils.COLOR_RED);
        line.setHasPoints(false);
        line.setStrokeWidth(3);
        lines.add(line);

        LineChartData data = new LineChartData();
        data = new LineChartData(lines);

        // Distance axis(bottom X) with formatter that will ad [km] to values, remember to modify max label charts
        // value.
        Axis distanceAxis = new Axis();
        distanceAxis.setName("tiempo");
        distanceAxis.setTextColor(ChartUtils.COLOR_ORANGE);
        distanceAxis.setMaxLabelChars(4);
        distanceAxis.setFormatter(new SimpleAxisValueFormatter().setAppendedText("ener".toCharArray()));
        distanceAxis.setHasLines(true);
        distanceAxis.setHasTiltedLabels(true);
        data.setAxisXBottom(distanceAxis);

        // Tempo uses minutes so I can't use auto-generated axis because auto-generation works only for decimal
        // system. So generate custom axis values for example every 15 seconds and set custom labels in format
        // minutes:seconds(00:00), you could do it in formatter but here will be faster.
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (float i = 0; i < tempoRange; i += 0.25f) {
            // I'am translating float to minutes because I don't have data in minutes, if You store some time data
            // you may skip translation.
            axisValues.add(new AxisValue(i).setLabel(formatMinutes(tempoRange - i)));
        }

        Axis tempoAxis = new Axis(axisValues).setName("Tempo [min/km]").setHasLines(true).setMaxLabelChars(4)
                .setTextColor(ChartUtils.COLOR_RED);
        data.setAxisYLeft(tempoAxis);


        // Set data
        chart2.setLineChartData(data);

        // Important: adjust viewport, you could skip this step but in this case it will looks better with custom
        // viewport. Set
        // viewport with Y range 0-12;
        Viewport v = chart2.getMaximumViewport();
        v.set(v.left, tempoRange, v.right, 0);
        chart2.setMaximumViewport(v);
        chart2.setCurrentViewport(v);
        grafica=chart2;

    }

    public void cargarGraficoSpeedChart(Context c){
        LineChartView chart2 = new LineChartView(c);
        Line line;
        List<PointValue> values;
        List<Line> lines = new ArrayList<Line>();

        // First good triangle
        values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 0).setLabel("".toCharArray()));
        values.add(new PointValue(1, 1).setLabel("Very Good:)".toCharArray()));
        values.add(new PointValue(2, 0).setLabel("".toCharArray()));

        line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setAreaTransparency(255);
        line.setFilled(true);
        line.setPointRadius(1);
        line.setHasLabels(true);
        lines.add(line);

        // Second good triangle
        values = new ArrayList<PointValue>();
        values.add(new PointValue(3, 0).setLabel("".toCharArray()));
        values.add(new PointValue(4, 0.5f).setLabel("Good Enough".toCharArray()));
        values.add(new PointValue(5, 0).setLabel("".toCharArray()));

        line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setAreaTransparency(255);
        line.setFilled(true);
        line.setPointRadius(1);
        line.setHasLabels(true);
        lines.add(line);

        // Bad triangle
        values = new ArrayList<PointValue>();
        values.add(new PointValue(1, 0).setLabel("".toCharArray()));
        values.add(new PointValue(2, -1).setLabel("Very Bad".toCharArray()));
        values.add(new PointValue(3, 0).setLabel("".toCharArray()));

        line = new Line(values);
        line.setColor(ChartUtils.COLOR_RED);
        line.setAreaTransparency(255);
        line.setFilled(true);
        line.setPointRadius(1);
        line.setHasLabels(true);
        lines.add(line);
        LineChartData data = new LineChartData();
        data = new LineChartData(lines);
        chart2.setLineChartData(data);
        grafica = chart2;

    }

    private String formatMinutes(float value) {
        StringBuilder sb = new StringBuilder();

        // translate value to seconds, for example
        int valueInSeconds = (int) (value * 60);
        int minutes = (int) Math.floor(valueInSeconds / 60d);
        int seconds = (int) valueInSeconds % 60;

        sb.append(String.valueOf(minutes)).append(':');
        if (seconds < 10) {
            sb.append('0');
        }
        sb.append(String.valueOf(seconds));
        return sb.toString();
    }
}
