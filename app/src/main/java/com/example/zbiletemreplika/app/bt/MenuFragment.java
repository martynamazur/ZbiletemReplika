package com.example.zbiletemreplika.app.bt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zbiletemreplika.R;

public class MenuFragment extends Fragment {

    private RecyclerView rcPayments;
    private RecyclerView rcAccount;
    private RecyclerView rcAboutUs;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }
}