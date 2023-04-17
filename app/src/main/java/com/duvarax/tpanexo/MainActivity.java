package com.duvarax.tpanexo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.duvarax.tpanexo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainAcitivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainAcitivityViewModel.class);

        mv.getLogeado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logeado) {

                Toast.makeText(MainActivity.this, "No fue posible logearse", Toast.LENGTH_LONG).show();


            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.logear(binding.etUsuario.getText()+"", binding.etContraseA.getText()+"");
            }
        });

    }
}