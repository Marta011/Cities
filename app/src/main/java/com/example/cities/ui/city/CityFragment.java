package com.example.cities.ui.city;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cities.R;
import com.example.cities.db.database.DatabaseClient;
import com.example.cities.db.entity.City;

import java.util.List;

public class CityFragment extends Fragment {

    View root;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_city, container, false);
        attributeWidgets();
        viewCities();
        return root;
    }

    void attributeWidgets() {
        recyclerView = root.findViewById(R.id.recyclerView_cities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void viewCities() {

        class ViewCities extends AsyncTask<Void, Void, List<City>> {

            @Override
            protected List<City> doInBackground(Void... voids) {
                return DatabaseClient
                        .getInstance(getActivity())
                        .getAppDatabase()
                        .cityDao()
                        .findAllCities();
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                super.onPostExecute(cities);
                CitiesAdapter adapter = new CitiesAdapter(getContext(), cities);
                recyclerView.setAdapter(adapter);
            }
        }
        ViewCities viewCities = new ViewCities();
        viewCities.execute();
    }
}