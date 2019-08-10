package com.example.juan.ahorratela;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;


public class ChartAdapter extends ArrayAdapter<Graficos> {

    public ChartAdapter(Context context, int resource, List<Graficos> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.cardchart, null);

            holder = new ViewHolder();
            holder.chartLayout = (FrameLayout) convertView.findViewById(R.id.chart_layout);
            holder.titulo = (TextView) convertView.findViewById(R.id.titulo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.chartLayout.setVisibility(View.VISIBLE);
        holder.chartLayout.removeAllViews();

        holder.titulo.setText(getItem(position).titulo);

        if (getItem(position).tipo == 1) {
            holder.chartLayout.addView((LineChartView) getItem(position).grafica);
        }
        if (getItem(position).tipo == 2) {
            holder.chartLayout.addView((PieChartView) getItem(position).grafica);
        }
        if (getItem(position).tipo == 3) {
            holder.chartLayout.addView((ColumnChartView) getItem(position).grafica);
        }
        if (getItem(position).tipo == 4) {
            //holder.chartLayout.addView((LineChartView) getItem(position).grafica);
        }
        if (getItem(position).tipo == 5) {
            //holder.chartLayout.addView((LineChartView) getItem(position).grafica);
        }

        return convertView;
    }

    private class ViewHolder {
        FrameLayout chartLayout;
        TextView titulo;
    }
}
