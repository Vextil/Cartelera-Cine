package com.siercuit.cartelera.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siercuit.cartelera.App;
import com.siercuit.cartelera.POJOs.PreciosEntradasPOJO;
import com.siercuit.cartelera.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PreciosEntradasAdapter extends ListAsGridAdapter
{
    private Context context;
    private PreciosEntradasPOJO data;

    public PreciosEntradasAdapter(Context context, PreciosEntradasPOJO data)
    {
        super(context);
        this.context = context;
        this.data = data;
    }

    public View getItemView(int position, View view, ViewGroup parent)
    {
        final ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (view == null) {
            view = inflater.inflate(R.layout.precios_entradas_grid_item, parent, false);
            holder = new ViewHolder(view);
            holder.nombre.setTypeface(App.getRobotoMediumTypeface());
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.opcionesList.removeAllViews();
        PreciosEntradasPOJO.Precios categoria = data.precios[position];
        holder.nombre.setText(categoria.nombre.toUpperCase());
        holder.nombre.setTextColor(App.getColor());
        PreciosEntradasPOJO.Precios.Opciones[] opciones = categoria.opciones;
        for (PreciosEntradasPOJO.Precios.Opciones opcion : opciones) {
            View opcionView = inflater.inflate(R.layout.precios_entradas_opciones_list_item, null, false);
            TextView opcionNombre = (TextView) opcionView.findViewById(R.id.nombre);
            opcionNombre.setTypeface(App.getRobotoTypeface());
            opcionNombre.setText(opcion.nombre);
            LinearLayout horariosList = (LinearLayout) opcionView.findViewById(R.id.horariosList);
            if (opcion.horarios != null) {
                PreciosEntradasPOJO.Precios.Opciones.Horarios[] horarios = opcion.horarios;
                int horariosLength = horarios.length;
                for (int ii = 0; ii < horariosLength; ++ii) {
                    PreciosEntradasPOJO.Precios.Opciones.Horarios horario = opcion.horarios[ii];
                    View horarioView = inflater.inflate(R.layout.precios_entradas_horarios_list_item, null, false);
                    TextView nombre = (TextView) horarioView.findViewById(R.id.nombre);
                    nombre.setTypeface(App.getRobotoTypeface());
                    nombre.setText(horario.nombre);
                    TextView nombreSub = (TextView) horarioView.findViewById(R.id.nombreSub);
                    nombreSub.setTypeface(App.getRobotoTypeface());
                    if (horario.nombre_sub != null) {
                        nombreSub.setText(horario.nombre_sub);
                        nombreSub.setVisibility(View.VISIBLE);
                    } else {
                        nombreSub.setVisibility(View.GONE);
                    }
                    if ((ii + 1) == horariosLength) {
                        horarioView.findViewById(R.id.divider).setVisibility(View.GONE);
                    }
                    TextView precio = (TextView) horarioView.findViewById(R.id.precio);
                    precio.setTypeface(App.getRobotoTypeface());
                    precio.setText(horario.precio);
                    precio.setTextColor(App.getColor());
                    horariosList.addView(horarioView);
                }
            }
            holder.opcionesList.addView(opcionView);
        }

        return view;
    }

    static class ViewHolder
    {
        @InjectView(R.id.nombre) TextView nombre;
        @InjectView(R.id.opcionesList) LinearLayout opcionesList;

        public ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public int getItemCount() { return data.precios.length; }

    @Override
    public Object getItem(int position) { return position; }

    @Override
    public long getItemId(int position) {
        return position;
    }

}