package com.example.crud_peliculas;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.crud_peliculas.datos.Pelicula;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Peliculas extends AppCompatActivity {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refPeliculas = database.getReference("peliculas");

    // Ordenamiento
    Query consultaOrdenada = refPeliculas.orderByChild("nombre");

    List<Pelicula> peliculas;
    ListView listaPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculas);

        inicializar();

    }

    private void inicializar() {
        FloatingActionButton fab_agregar= findViewById(R.id.fab_agregar);
        listaPersonas = findViewById(R.id.ListaPersonas);

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AgregarPelicula.class);

                intent.putExtra("accion","e"); // Editar
                intent.putExtra("key", peliculas.get(i).getKey());
                intent.putExtra("titulo",peliculas.get(i).getTitulo());
                intent.putExtra("desc",peliculas.get(i).getDesc());
                intent.putExtra("year",peliculas.get(i).getYear());
                intent.putExtra("rate",peliculas.get(i).getRate());

                startActivity(intent);
            }
        });

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaPersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(Peliculas.this);
                ad.setMessage("Está seguro de eliminar registro?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Peliculas.refPeliculas
                                .child(peliculas.get(position).getKey()).removeValue();

                        Toast.makeText(Peliculas.this,
                                "Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(Peliculas.this,
                                "Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });

        fab_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent i = new Intent(getBaseContext(), AgregarPelicula.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("titulo","");
                i.putExtra("desc","");
                i.putExtra("year","");
                i.putExtra("rate","");

                startActivity(i);
            }
        });

        peliculas = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                peliculas.removeAll(peliculas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Pelicula pelicula = dato.getValue(Pelicula.class);
                    pelicula.setKey(dato.getKey());
                    peliculas.add(pelicula);
                }

                ListarPeliculas adapter = new ListarPeliculas(Peliculas.this,
                        peliculas );
                listaPersonas.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
