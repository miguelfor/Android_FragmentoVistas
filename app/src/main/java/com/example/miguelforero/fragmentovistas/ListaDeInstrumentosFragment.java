package com.example.miguelforero.fragmentovistas;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListaDeInstrumentosFragment extends ListFragment {

    private IInstrumentoSeleccionado implementacion;

    public interface IInstrumentoSeleccionado {
        void instrumentoSeleccionado(int posicion);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            implementacion = (IInstrumentoSeleccionado) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString()
                    + " debe implementar IInstrumentosSeleccionado.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LLenado loista de Instrumentos desde DB
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        setListAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, baseDeDatos.intrumentos));

        /*Llenado desde Strings.xml
        String[] instrumentos = getResources().getStringArray(R.array.instrumentos);
        setListAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, instrumentos));*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int posicion, long id) {
        implementacion.instrumentoSeleccionado(posicion);
        getListView().setItemChecked(posicion, true);
    }
}
