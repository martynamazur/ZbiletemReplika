package com.example.zbiletemreplika.app.bt.myTickets.lastCities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zbiletemreplika.R;
import com.example.zbiletemreplika.app.bt.buyTickets.TicketsAdapter;

import java.util.ArrayList;

public class LastVisitedCitiesAdapter extends RecyclerView.Adapter<LastVisitedCitiesAdapter.ViewHolder>{

    private ArrayList<String> lastCities;

    public LastVisitedCitiesAdapter(ArrayList<String> lastCities) {
        this.lastCities = lastCities;
    }

    @NonNull
    @Override
    public LastVisitedCitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_cities_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastVisitedCitiesAdapter.ViewHolder holder, int position) {
        String cityName = lastCities.get(position);
        holder.cityName.setText(cityName);

    }

    @Override
    public int getItemCount() {
        return lastCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button cityName;
        public ViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
        }
    }
}
