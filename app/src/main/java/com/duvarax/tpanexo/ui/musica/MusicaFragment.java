package com.duvarax.tpanexo.ui.musica;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.tpanexo.R;
import com.duvarax.tpanexo.databinding.FragmentMusicaBinding;

public class MusicaFragment extends Fragment {

    private MusicaViewModel mViewModel;
    private FragmentMusicaBinding binding;

    public static MusicaFragment newInstance() {
        return new MusicaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMusicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MusicaViewModel.class);
        // TODO: Use the ViewModel
    }

}