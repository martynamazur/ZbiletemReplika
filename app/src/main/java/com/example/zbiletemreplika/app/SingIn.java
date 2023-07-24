package com.example.zbiletemreplika.app;

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

public class SingIn extends AppCompatActivity {

    private Button singInBtn;
    private CheckBox checkBoxAcceptTerms;
    private EditText phoneNumber;
    private EditText phoneNumberPrefix;
    private EditText messageWarningTerms;
    private SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.sing_in_out_layout);

        singInBtn = findViewById(R.id.singInBtn);
        checkBoxAcceptTerms = findViewById(R.id.checkBoxAcceptTerms);
        phoneNumber = findViewById(R.id.phoneNumber);
        phoneNumberPrefix = findViewById(R.id.phoneNumberPrefix);



        singInBtn.setOnClickListener(view -> {
            //przejdz do weryfikacji jesli zaakceptowany zostal regulamin
            if (checkBoxAcceptTerms.isChecked()){
                Intent intent = new Intent();
                startActivity(intent);
            }else{
                //wyswietl komunikat, ze musi zostac zaakceptowany
            }


        });
    }
}
