package models;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import models.Item;

public class SharedViewModel extends ViewModel {

    /**
     * Valor que reflejará para edición (o adicion);M
     */
    MutableLiveData<Item> dataIn = new MediatorLiveData<>();

    public MutableLiveData<Item> getDataIn() {
        return dataIn;
    }

    public void setDataIn(Item dataIn) {
        this.dataIn.setValue(dataIn);
    }

    /**
     * Valor que reflejará al fragment de la lista
     */
    MutableLiveData<Item> dataOut = new MediatorLiveData<>();

    public MutableLiveData<Item> getDataOut() {
        return dataOut;
    }

    public void setDataOut(Item dataOut) {
        this.dataOut.setValue(dataOut);
    }

    /*
    private Item itemToUpdateCreate;

    public Item getItemToUpdateCreate() {
        return itemToUpdateCreate;
    }

    public void setItemToUpdateCreate(Item itemToUpdateCreate) {
        this.itemToUpdateCreate = itemToUpdateCreate;
    }*/
}
