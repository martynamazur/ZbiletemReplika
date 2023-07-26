package com.example.zbiletemreplika.app.bt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zbiletemreplika.R;
import com.example.zbiletemreplika.app.bt.buyTickets.TicketsAdapter;
import com.example.zbiletemreplika.app.bt.buyTickets.TicketsModel;
import com.example.zbiletemreplika.app.bt.myTickets.lastCities.LastVisitedCitiesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyFragment extends Fragment {

    private RecyclerView recyclerView;
    private TicketsAdapter ticketsAdapter;
    private RecyclerView recyclerViewLastVisitedLocalization;
    private SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fragment, container, false);

        recyclerView = view.findViewById(R.id.buyRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ticketsAdapter = new TicketsAdapter(new ArrayList<>());
        recyclerView.setAdapter(ticketsAdapter);

        // Shared pref
        String selectedCity = "Warsaw";
        loadTicketsData(selectedCity);

        // usunac i zainicjalizowac z sharefPref
       ArrayList<String> miastaTest = new ArrayList<>(Arrays.asList("Wraszawa","Katowice","Bydgoszcz"));
       LastVisitedCitiesAdapter lastVisitedCitiesAdapter = new LastVisitedCitiesAdapter(miastaTest);
       recyclerViewLastVisitedLocalization = view.findViewById(R.id.lastLocalization);
       recyclerViewLastVisitedLocalization.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       recyclerViewLastVisitedLocalization.setAdapter(lastVisitedCitiesAdapter);

        return view;
    }

    // Metoda wczytująca dane z bazy danych Firebase i aktualizująca adapter
    private void loadTicketsData(String cityName) {
        DatabaseReference ticketsRef = FirebaseDatabase.getInstance().getReference().child("availableTickets").child(cityName).child("ticketTypes");
        ticketsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Tutaj obsługujemy dane wczytane z bazy danych
                if (snapshot.exists()) {
                    ArrayList<TicketsModel> ticketList = new ArrayList<>();
                    for (DataSnapshot ticketSnapshot : snapshot.getChildren()) {
                        // Tworzenie obiektu TicketsModel z danych z bazy danych
                        TicketsModel ticket = ticketSnapshot.getValue(TicketsModel.class);
                        ticketList.add(ticket);
                    }

                    // Aktualizacja adaptera z wczytanymi danymi
                    ticketsAdapter.setTicketList(ticketList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Obsługa błędów
            }
        });
    }
}
