package com.example.tareas2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener , lista_tareasf.OnFragmentInteractionListener,vista_tareaf.OnFragmentInteractionListener {
    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;

    Toolbar toolbar;

   // private Adapter adapter;

    lista_tareasf fragmentoListaTareas = new lista_tareasf();
    vista_tareaf fragmentoVistaTarea = new vista_tareaf();

    View.OnClickListener listenerLista = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            floatingActionButton.setImageResource(R.drawable.ic_add_black_24dp);
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.drawerLayout_principal, fragmentoListaTareas);
            fragmentTransaction1.commit();//usar add, para hacer varios fragments a la vez.
        }
    };

    View.OnClickListener listenerVistaTarea = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            floatingActionButton.setImageResource(R.drawable.ic_check_black_24dp);
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.drawerLayout_principal, fragmentoVistaTarea);
            fragmentTransaction1.commit();
        }
    };

    FloatingActionButton floatingActionButton;
    //indica a que fragment quiero ir
    private void setListenerFAB(int idResource){
        switch(idResource){
            case R.drawable.ic_add_black_24dp:
                floatingActionButton.setImageResource(idResource);

                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.drawerLayout_principal, fragmentoListaTareas);
                fragmentTransaction1.commit();//usar add, para hacer varios fragments a la vez.
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.drawerLayout_principal, fragmentoVistaTarea);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();//usar add, para hacer varios fragments a la vez.

                    }
                });
                break;
            case R.drawable.ic_check_black_24dp:
                floatingActionButton.setImageResource(idResource);

                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.drawerLayout_principal, fragmentoVistaTarea);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();//usar add, para hacer varios fragments a la vez.

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                    }
                });
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentoVistaTarea.setComunicacionFragments(new ComunicarFragmentsTemp() {
            @Override
            public void enviarMensaje(Item item,int listener, int recursoId) {

                //setListenerFAB(recursoId);
                if (listener == 1) {
                    listenerLista.onClick(null);
                    floatingActionButton.setOnClickListener(listenerVistaTarea);
                    floatingActionButton.setImageResource(recursoId);
                    fragmentoListaTareas.addItemToAdapter(item);

                } else {
                    listenerVistaTarea.onClick(null);
                    floatingActionButton.setOnClickListener(listenerLista);
                    floatingActionButton.setImageResource(recursoId);
                    fragmentoListaTareas.addItemToAdapter(item);

                }

            }
        });

        getSupportFragmentManager().beginTransaction().
                add(R.id.drawerLayout_principal, fragmentoListaTareas).commit();

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragmentoListaTareas.addItemToAdapter(new Item("aaaa", "asdasd", false));
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drawerLayout_principal, fragmentoVistaTarea);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();//usar add, para hacer varios fragments a la vez.

                floatingActionButton.setImageResource(R.drawable.ic_check_black_24dp);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentoVistaTarea.call();//para que guarde todo en un item, y lo regrese, con el id del icono del boton flotante y se setee el nuevo evvento

                    }
                });

            }
        });

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

    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(menuItem.getItemId()== R.id.btn_ayuda){
            //adapter.addItem(new Item("aaaa", "asdasd", false));
            return true;
        }
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return mToogle.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println(uri);
    }



}
