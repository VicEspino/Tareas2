package com.example.tareas2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    //esta mamada notifica que su valor changed
    private MutableLiveData<Item> itemMutableLiveData = new MutableLiveData<>();

    public void setItemMutableLiveData(Item itemMutableLiveData){
        this.itemMutableLiveData.postValue(itemMutableLiveData);
    }

    public LiveData<Item> getItemMutableLiveData(){
        return itemMutableLiveData;
    }
}
