package archivos;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Item;

public class PersistenciaTareas {


    public static final String RUTA_TAREAS =Environment.getExternalStorageDirectory()+"/tareas.txt";

    private List<Item> items;


    private PersistenciaTareas() {
    }

    public PersistenciaTareas(List<Item> items) {
        if(items!=null)
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

    private static PersistenciaTareas initConfig(){
        FileWriter fWriter;
        File sdCardFile = new File(RUTA_TAREAS);
        Log.d("TAG", sdCardFile.getPath()); //<-- check the log to make sure the path is correct.

        PersistenciaTareas pt = new PersistenciaTareas(null);
        Gson gson = new Gson();

        try{
            fWriter = new FileWriter(sdCardFile);
            //fWriter.write("Inicio archivo");
            gson.toJson(pt,fWriter);
           // fWriter.flush();
            //fWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return pt;
    }
    public static PersistenciaTareas getConfiguracion()
    {
        Gson gson = new Gson();
        PersistenciaTareas persistenciaTareas=null;
        try
        {
            persistenciaTareas= gson.fromJson(new FileReader(RUTA_TAREAS),PersistenciaTareas.class);
        }
        catch (FileNotFoundException ex)
        {
            persistenciaTareas = initConfig();// genera el archivo por defecto .json donde se almacenarán las credenciales para el inicio de decisión
            Logger.getLogger(PersistenciaTareas.class.getName()).log(Level.INFO, "No encuentra ruta, pero la crea.", ex);
        }
        return persistenciaTareas;
    }

    public static void setConfiguracion(PersistenciaTareas sharePreferences)
    {
        Gson gson = new Gson();
        FileWriter writer=null;
        File sdCardFile = new File(RUTA_TAREAS);

        try
        {
            writer = new FileWriter(sdCardFile);
            gson.toJson(sharePreferences, writer);
        }
        catch (IOException ex)
        {
            Logger.getLogger(PersistenciaTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(PersistenciaTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
