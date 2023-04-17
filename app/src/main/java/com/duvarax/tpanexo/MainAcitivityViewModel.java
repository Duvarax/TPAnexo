package com.duvarax.tpanexo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainAcitivityViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> logeado;
    private Context context;
    public MainAcitivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Boolean> getLogeado(){
        if(logeado == null){
            logeado = new MutableLiveData<>();
        }
        return logeado;
    }

    public void logear(String usuario, String contraseña){
        System.out.println(usuario.equals("admin"));
        if(usuario.equals("admin") && contraseña.equals("admin")){
            Intent intent = new Intent(context, NavigationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }else{
            logeado.setValue(false);
        }
    }

}
