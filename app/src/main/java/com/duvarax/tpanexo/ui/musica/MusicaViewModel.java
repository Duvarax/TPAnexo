package com.duvarax.tpanexo.ui.musica;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.tpanexo.R;

public class MusicaViewModel extends ViewModel {

    private MutableLiveData<Boolean> reproducir;
    public MutableLiveData<Boolean> detener;

    public MusicaViewModel() {
    }

    public LiveData<Boolean> getReproductor(){
        if(reproducir == null){
            reproducir = new MutableLiveData<>();
        }
        return reproducir;
    }

    public LiveData<Boolean> getDetener(){
        if(detener == null){
            detener = new MutableLiveData<>();
        }
        return detener;
    }

    public void reproducirMusica(){reproducir.setValue(true);}
    public void detenerMusica(){detener.setValue(true);};

    // TODO: Implement the ViewModel



}
