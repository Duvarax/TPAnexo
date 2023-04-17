package com.duvarax.tpanexo.ui.musica;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.tpanexo.R;
import com.duvarax.tpanexo.databinding.FragmentMusicaBinding;
import com.duvarax.tpanexo.ui.salir.SalirViewModel;

public class MusicaFragment extends Fragment {


    private MusicaViewModel mViewModel;
    public FragmentMusicaBinding binding;
    private Intent servicioMusical;

    public static MusicaFragment newInstance() {
        return new MusicaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMusicaBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MusicaViewModel.class);
        View view = binding.getRoot();
        mViewModel.getReproductor().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                servicioMusical = new Intent(getActivity(), ServicioMusical.class);
                servicioMusical.putExtra("tema", R.raw.megolovania);
                getActivity().startService(servicioMusical);
            }
        });

        mViewModel.getDetener().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(servicioMusical != null){
                    getActivity().stopService(servicioMusical);
                    servicioMusical = null;
                }
            }
        });



        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.reproducirMusica();
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.detenerMusica();
            }
        });
        return view;




    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }



}