package com.example.tareas2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link vista_tareaf.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link vista_tareaf#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vista_tareaf extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText edMateria;
    EditText edTarea;
   // private SharedViewModel viewModel;
    private FloatingActionButton floatingActionButton;
    private Item objetoTarea;

    public vista_tareaf() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment vista_tareaf.
     */
    // TODO: Rename and change types and number of parameters
    public static vista_tareaf newInstance(String param1, String param2) {
        vista_tareaf fragment = new vista_tareaf();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contenedorFragment = inflater.inflate(R.layout.fragment_vista_tareaf, container, false);
        edMateria = contenedorFragment.findViewById(R.id.editText_Materia);
        edTarea = contenedorFragment.findViewById(R.id.editText_tarea);
        this.floatingActionButton = contenedorFragment.findViewById(R.id.fab);
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((cambiarFragment)getActivity()).goFragmentLista(/*objetoTarea*/);//dejar solo cambio de fragment sin argumento
                if(objetoTarea.isActive()){//si es inactivo no modifica nada
                    objetoTarea.setTitle(edMateria.getText().toString());
                    objetoTarea.setSubtitle(edTarea.getText().toString());
                    objetoTarea.setActive(stateItemShowed);
                    objetoTarea.notificarCambio();
                }

            }
        });

        if(this.getArguments()!=null){
            Item objeto_tarea = (Item) this.getArguments().getSerializable("objeto_tarea");
            if(objeto_tarea!=null ){

                    this.objetoTarea = objeto_tarea;

                    edMateria.setText(objeto_tarea.getTitle());
                    edTarea.setText(objeto_tarea.getSubtitle());
                    this.stateItemShowed = objeto_tarea.isActive();


            }

        }

        return contenedorFragment;
    }
    private boolean stateItemShowed = true;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}