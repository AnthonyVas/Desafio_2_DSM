package com.example.crud_peliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.crud_peliculas.datos.Pelicula;

public class MainActivity extends AppCompatActivity {
    EditText txtTitulo, txtDesc, txtYear, txtRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}