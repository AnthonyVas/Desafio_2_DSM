package com.example.crud_peliculas;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crud_peliculas.datos.Pelicula;

import java.util.List;

public class ListarPeliculas extends ArrayAdapter<Pelicula> {

    List<Pelicula> peliculas;
    private Activity context;

    public ListarPeliculas(@NonNull Activity context, @NonNull List<Pelicula> peliculas) {
        super(context, R.layout.pelicula, peliculas);
        this.context = context;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        if (view == null)
            rowview = layoutInflater.inflate(R.layout.pelicula,null);
        else rowview = view;

        TextView txtTitulo = rowview.findViewById(R.id.txtTitulo);
        TextView txtDesc = rowview.findViewById(R.id.txtDesc);
        TextView txtYear = rowview.findViewById(R.id.txtYear);
        TextView txtRate = rowview.findViewById(R.id.txtRate);

        txtTitulo.setText("Titulo : "+peliculas.get(position).getTitulo());
        txtDesc.setText("Descripcion : " + peliculas.get(position).getDesc());
        txtYear.setText("Año de extreno : " + peliculas.get(position).getYear());
        txtRate.setText("Calificacion : " + peliculas.get(position).getRate());

        return rowview;
    }
}