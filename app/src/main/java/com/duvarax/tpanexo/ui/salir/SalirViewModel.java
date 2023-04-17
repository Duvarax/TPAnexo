package com.duvarax.tpanexo.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Boolean> dialogo;

    public SalirViewModel() {
    }

    public LiveData<Boolean> getDialogo(){
        if(dialogo == null){
            dialogo = new MutableLiveData<>();
        }
        return dialogo;
    }

    public void abrirDialogo(){
        dialogo.setValue(true);
    }
}