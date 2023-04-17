package com.duvarax.tpanexo.ui.ubicacion;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.tpanexo.NavigationActivity;
import com.duvarax.tpanexo.R;
import com.duvarax.tpanexo.databinding.FragmentUbicacionBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class UbicacionFragment extends Fragment {

    private UbicacionViewModel mViewModel;
    private FragmentUbicacionBinding binding;
    private FusedLocationProviderClient fused;
    private Context context;
    public LatLng ubicacion;

    public static UbicacionFragment newInstance() {
        return new UbicacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =FragmentUbicacionBinding.inflate(inflater, container, false);
        context = this.getContext();
        View root = binding.getRoot();
        obtenerUltimaUbicacion();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UbicacionViewModel.class);
        // TODO: Use the ViewModel
    }
    public void obtenerUltimaUbicacion() {
        fused = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    ubicacion = new LatLng(location.getLatitude(), location.getLongitude());
                    SupportMapFragment smf = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
                    smf.getMapAsync(new miMapa());
                }
            }
        });
    }
    private class miMapa implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(ubicacion).title("Yo"));
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(ubicacion)
                    .zoom(19)
                    .bearing(45)
                    .tilt(60)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);

            googleMap.animateCamera(update);
        }
    }


}