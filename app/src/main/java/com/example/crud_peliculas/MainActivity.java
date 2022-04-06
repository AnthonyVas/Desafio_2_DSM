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

    private void inicializar() {
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDesc = findViewById(R.id.txtDesc);
        txtYear = findViewById(R.id.txtYear);
        txtRate = findViewById(R.id.txtRate);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
//        titulo = datos.getString("key");
//        dui = datos.getString("dui");
//        nombre=datos.getString("nombre");
//        accion=datos.getString("accion");
//        edtDUI.setText(dui);
//        edtNombre.setText(nombre);
    }

    public void guardar(View v){
        String titulo = txtTitulo.getText().toString();
        String desc = txtDesc.getText().toString();
        String year = txtYear.getText().toString();
        String rate = txtRate.getText().toString();

        // Se forma objeto persona
        Pelicula persona = new Pelicula(titulo,desc,year,rate);

        if (accion.equals("a")) { //Agregar usando push()
            Peliculas.refPersonas.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            Peliculas.refPersonas.child(key).setValue(persona);
        }
        finish();
    }
}