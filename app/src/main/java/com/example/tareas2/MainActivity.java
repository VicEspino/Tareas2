package com.example.tareas2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import archivos.PersistenciaTareas;
import models.CambiarFragment;
import models.Item;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener, CambiarFragment {
    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActionMode actionMode;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    ListaTareasFragment listaTareasFragment = new ListaTareasFragment();
    VistaTareaFragment vistaTareaFragment = new VistaTareaFragment();

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(MainActivity.this, vista_tarea.class);
                //startActivity(intent);
                goToVistaFragment();
            }
        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

        }
        toolbar =findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);//ordenar bien estas instrucciones, para que sirva la animacion del boton menu

        mDrawerLayout = findViewById(R.id.drawerLayout_principal);
        mToogle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        //toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToogle.syncState();
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Objects.requireNonNull(getSupportActionBar()).setElevation(50f);
        // //tenia una elevacion el floatbuton, por eso no me dejaba,al darle más elevacion a la barra, se detectaba esta

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frameLayout_containerFragment, listaTareasFragment).commit();

        //PersistenciaTareas persistenciaTareas =  PersistenciaTareas.getConfiguracion();
        //persistenciaTareas.getItems();
       /* Item item = new Item("Quimica", "Comer", true);
        Item item2 = new Item("Quimica2", "Comer", true);

        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);

        PersistenciaTareas.setConfiguracion(new PersistenciaTareas(items));
*/
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return mToogle.onOptionsItemSelected(item);
    }


    @Override
    public void goToVistaFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_containerFragment,vistaTareaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }



 }
