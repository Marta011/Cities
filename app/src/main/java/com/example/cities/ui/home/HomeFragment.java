package com.example.cities.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cities.MainActivity;
import com.example.cities.R;
import com.example.cities.ui.city.CityFragment;
import com.example.cities.ui.googleMaps.MapsActivity;

public class HomeFragment extends Fragment {

    LinearLayout linearLayoutCities, linearLayoutGoogleMap;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        attributeWidgets();
        setOnClickLinearLayouts();
        return root;
    }

    private void attributeWidgets() {
        linearLayoutCities = root.findViewById(R.id.linearLayout_cities);
        linearLayoutGoogleMap = root.findViewById(R.id.linearLayout_googleMap);
    }

    private void setOnClickLinearLayouts() {
        linearLayoutCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new CityFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        linearLayoutGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}