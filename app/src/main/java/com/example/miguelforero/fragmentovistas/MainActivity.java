package com.example.miguelforero.fragmentovistas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements ListaDeInstrumentosFragment.IInstrumentoSeleccionado {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.contenedor) != null) {
            if (savedInstanceState != null)
                return;

            ListaDeInstrumentosFragment fragment = new ListaDeInstrumentosFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor, fragment)
                    .commit();
        }
    }

    @Override
    public void instrumentoSeleccionado(int posicion) {
        InstrumentoFragment fragment = (InstrumentoFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);
        if (fragment != null) {
            fragment.actualizarVista(posicion);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, InstrumentoFragment.getInstance(posicion))
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void favorito(View view){
        Toast.makeText(MainActivity.this , "Instrumento añadido a tus favoritos", Toast.LENGTH_SHORT).show();
    }

    public void play(View view){
        Toast.makeText(MainActivity.this , "Reproduciendo sonido del instrumento", Toast.LENGTH_SHORT).show();
    }
}
