package com.example.zbiletemreplika.app.menu.payments;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zbiletemreplika.R;

public class PaymentsMethod extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.payments_method_layout);

        // w zaleznosci od wybranego rodzaju, dalej przenosi na serwis zewnetrzny
        // lub wpisuje kod blik
        // lub przenosze sie do okna podpiecia karty planiczej

    }
}
