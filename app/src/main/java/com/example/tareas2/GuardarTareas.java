package com.example.tareas2;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Item;

public class GuardarTareas {

    public static final String CONFIGURACION_DB="tareas.json";

    private List<Item> items;


    private GuardarTareas(List<Item> items) {
        if(items !=null)
            this.items = items;
        else
            this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static GuardarTareas initConfig()
    {
        Writer writer =null;
        GuardarTareas guardarTareas = new GuardarTareas(null);
        Gson gson = new Gson();
        try
        {

             writer  = new FileWriter(Environment.getDataDirectory() +CONFIGURACION_DB);
            gson.toJson(guardarTareas,writer);

        }
        catch (IOException ex)
        {
            Logger.getLogger(GuardarTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(GuardarTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //HACER que las preferencias sean estaticas para desde aquí devolver la instancia sharepreferences por defecto.
        return guardarTareas;
    }

    public static GuardarTareas getTareas()
    {
        Gson gson = new Gson();
        GuardarTareas sharedPreferences=null;
        try
        {
            sharedPreferences= gson.fromJson(new FileReader(CONFIGURACION_DB),GuardarTareas.class);
        }
        catch (FileNotFoundException ex)
        {//si no encuentra el archivo, genera las configuraciones por defecto.
            sharedPreferences = initConfig();// genera el archivo por defecto .json donde se almacenarán las credenciales para el inicio de decisión
            Logger.getLogger(GuardarTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sharedPreferences;
    }

    public static void setTareas(GuardarTareas guardarTareas)
    {
        Gson gson = new Gson();
        Writer writer=null;
        try
        {
            writer = new FileWriter(CONFIGURACION_DB);
            gson.toJson(guardarTareas, writer);
        }
        catch (IOException ex)
        {
            Logger.getLogger(GuardarTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(GuardarTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
