package com.example.zbiletemreplika.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zbiletemreplika.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingIn extends AppCompatActivity {

    private Button singInBtn;
    private CheckBox checkBoxAcceptTerms;
    private EditText phoneNumber;
    private EditText phoneNumberPrefix;
    private EditText messageWarningTerms;
    private SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in_out_layout);

        singInBtn = findViewById(R.id.singInBtn);
        checkBoxAcceptTerms = findViewById(R.id.checkBoxAcceptTerms);
        phoneNumber = findViewById(R.id.phoneNumber);
        phoneNumberPrefix = findViewById(R.id.phoneNumberPrefix);
        preferences = getSharedPreferences("logInPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();



        singInBtn.setOnClickListener(view -> {
            //przejdz do weryfikacji jesli zaakceptowany zostal regulamin
            if (checkBoxAcceptTerms.isChecked()){

                String phoneNumberText = phoneNumber.getText().toString();
                String phoneNumberPrefixText = phoneNumberPrefix.getText().toString();

                // Upewnij się, że prefiks kraju zawiera tylko cyfry
                String cleanedPhoneNumberPrefix = phoneNumberPrefixText.replaceAll("[^\\d]", "");

                // Sklej numer telefonu z prefiksem kraju i dodaj znak plusa
                String phoneNumberFormat = "+" + cleanedPhoneNumberPrefix + phoneNumberText;
                System.out.println("ttest " + phoneNumberFormat);

                // Zapisz numer telefonu w SharedPreferences
                editor.putString("phoneNumber", phoneNumberFormat);
                editor.apply();

                Intent intent = new Intent(SingIn.this, Verification.class);
                startActivity(intent);
            }else{
                //wyswietl komunikat, ze musi zostac zaakceptowany
            }


        });
    }
}
