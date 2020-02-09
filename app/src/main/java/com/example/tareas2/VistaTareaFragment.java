package com.example.tareas2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import models.Item;
import models.SharedViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VistaTareaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VistaTareaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txtTarea;
    private EditText txtMateria;
    private SharedViewModel sharedViewModel;
    private boolean estadoTareaActual = true;

    public VistaTareaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VistaTareaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VistaTareaFragment newInstance(String param1, String param2) {
        VistaTareaFragment fragment = new VistaTareaFragment();
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
        View contenedor = inflater.inflate(R.layout.fragment_vista_tarea, container, false);

        this.txtMateria = (EditText)contenedor.findViewById(R.id.editText_materia);
        this.txtTarea = (EditText)contenedor.findViewById(R.id.editText_tarea);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_check_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
                getFragmentManager().popBackStack();
            }
        });

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getDataIn().observe(getViewLifecycleOwner(), new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                if(item!=null){
                    txtMateria.setText(item.getTitle());
                    txtTarea.setText(item.getSubtitle());
                    estadoTareaActual = item.isActive();
                    //falta guardar estado
                }else{
                    txtMateria.setText("");
                    txtTarea.setText("");
                    estadoTareaActual = false;//cuando el objeto es nulo, significa nueva tarea, por lo tanto
                }
            }
        });

        return contenedor;
    }

    public void guardarCambios(){
        if(!estadoTareaActual)//cuando sea falso (tarea pendiente) se añadirá a la lista del otro fragment
            sharedViewModel.setDataOut(
                    new Item(txtMateria.getText().toString(), txtTarea.getText().toString(), estadoTareaActual)
            );

    }

}
