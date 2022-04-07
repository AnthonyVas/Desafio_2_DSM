package com.example.crud_peliculas;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.crud_peliculas.datos.Pelicula;

public class AgregarPelicula extends AppCompatActivity {
    EditText txtTitulo, txtDesc, txtYear, txtRate;
    String key="",titulo="",desc="",accion="", year = "", rate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregara_pelicula);
        inicializar();

    }

    private void inicializar() {
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDesc = findViewById(R.id.txtDesc);
        txtYear = findViewById(R.id.txtYear);
        txtRate = findViewById(R.id.txtRate);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        titulo = datos.getString("titulo");
        desc=datos.getString("desc");
        year=datos.getString("year");
        rate=datos.getString("rate");
        accion=datos.getString("accion");

        txtTitulo.setText(titulo);
        txtDesc.setText(desc);
        txtRate.setText(rate);
        txtYear.setText(year);

    }

    public void guardar(View v){
        String titulo = txtTitulo.getText().toString();
        String desc = txtDesc.getText().toString();
        String year = txtYear.getText().toString();
        String rate = txtRate.getText().toString();

        // Se forma objeto persona
        Pelicula pelicula = new Pelicula(titulo,desc,year,rate);

        if (accion.equals("a")) { //Agregar usando push()
            Peliculas.refPeliculas.push().setValue(pelicula);
        }
        else // Editar usando setValue
        {
            Peliculas.refPeliculas.child(key).setValue(pelicula);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }


}
