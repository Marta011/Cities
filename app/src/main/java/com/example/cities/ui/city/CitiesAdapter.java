package com.example.cities.ui.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cities.R;
import com.example.cities.db.entity.City;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {
    private Context context;
    private List<City> cityList;

    CitiesAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_card_city, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        holder.txtName.setText(cityList.get(position).getName());
        holder.txtCountry.setText(cityList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    static class CitiesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtCountry;

        CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_Name);
            txtCountry = itemView.findViewById(R.id.txt_Country);
        }
    }
}
