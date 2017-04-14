package com.example.miguelforero.fragmentovistas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Miguel Forero on 13/04/2017.
 */

public class InstrumentoFragment extends Fragment {
    private static final String LLAVE_POSICION = "posicion";

    private int posicionActual = -1;

    public static InstrumentoFragment getInstance(int posicion) {
        InstrumentoFragment fragment = new InstrumentoFragment();

        Bundle argumentos = new Bundle();
        argumentos.putInt(LLAVE_POSICION, posicion);

        fragment.setArguments(argumentos);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null)
            posicionActual = savedInstanceState.getInt(LLAVE_POSICION);

        return inflater.inflate(R.layout.fragment_instrumento, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle argumentos = getArguments();
        if (argumentos != null)
            actualizarVista(argumentos.getInt(LLAVE_POSICION));
        else if (posicionActual != -1)
            actualizarVista(posicionActual);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(LLAVE_POSICION, posicionActual);
    }

    public void actualizarVista(int posicion) {
            String[] instrumentos = getResources().getStringArray(R.array.instrumentos);
            String[] descripciones = getResources().getStringArray(R.array.descripcion);

            ((TextView) getActivity().findViewById(R.id.text_descripcion)).setText(descripciones[posicion]);
            getActivity().setTitle("Instrumentos de " + instrumentos[posicion]);

            if(posicion==3)
                getActivity().setTitle("Instrumentos " + instrumentos[posicion]);

            int color = ContextCompat.getColor(getContext(), android.R.color.transparent);
            Drawable iconInstrumento = null;
            Drawable iconInstrumento2 = null;
            switch (posicion) {
                case 0:
                    iconInstrumento = ContextCompat.getDrawable(getContext(), R.drawable.cuerda1);
                    iconInstrumento2 = ContextCompat.getDrawable(getContext(), R.drawable.cuerda2);
                    break;
                case 1:
                    iconInstrumento = ContextCompat.getDrawable(getContext(), R.drawable.percucion1);
                    iconInstrumento2 = ContextCompat.getDrawable(getContext(), R.drawable.percucion2);
                    break;
                case 2:
                    iconInstrumento = ContextCompat.getDrawable(getContext(), R.drawable.viento1);
                    iconInstrumento2 = ContextCompat.getDrawable(getContext(), R.drawable.viento2);
                    break;
                case 3:
                    iconInstrumento = ContextCompat.getDrawable(getContext(), R.drawable.electricos1);
                    iconInstrumento2 = ContextCompat.getDrawable(getContext(), R.drawable.electricos2);
                    break;
            }

        ((ImageView) getActivity().findViewById(R.id.img_instrumento)).setImageDrawable(iconInstrumento);
        ((ImageView) getActivity().findViewById(R.id.img_instrumento2)).setImageDrawable(iconInstrumento2);


        }


    }

