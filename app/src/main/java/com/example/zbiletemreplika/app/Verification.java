package com.example.zbiletemreplika.app;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zbiletemreplika.R;
import com.example.zbiletemreplika.app.bt.ActivityTransition;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private SharedPreferences preferences;
    private Button checkCodeBtn;
    private TextInputEditText inputCodeUser;
    private String mVerificationId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_verification_layout);

        checkCodeBtn = findViewById(R.id.checkCodeBtn);
        inputCodeUser = findViewById(R.id.inputCode);

        mAuth = FirebaseAuth.getInstance();
        preferences = getSharedPreferences("logInPref", MODE_PRIVATE);
        String phoneNumber = preferences.getString("phoneNumber", "");


        checkCodeBtn.setOnClickListener(view -> {
            String userInput = inputCodeUser.getText().toString();
            verifyVerificationCode(userInput);
        });





        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:");

                // If verification is successful, you can now sign in the user using the provided credential.
                // !!!
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                PhoneAuthProvider.ForceResendingToken mResendToken = token;

            }
        };



        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // Helper method to sign in the user with the provided phone auth credentials
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        // You can access the authenticated user using task.getResult().getUser() and proceed with the next steps.
                        Intent intent = new Intent(Verification.this, ActivityTransition.class); // Zamiast NextActivity podaj nazwę klasy aktywności, do której chcesz się przenieść
                        startActivity(intent);
                        finish();
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                });
    }
    // Metoda do weryfikacji kodu weryfikacyjnego
    private void verifyVerificationCode(String userInputCode) {
        // Tworzymy PhoneAuthCredential na podstawie otrzymanego kodu weryfikacyjnego oraz mVerificationId
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, userInputCode);

        // Wywołujemy metodę signInWithPhoneAuthCredential, aby sprawdzić poprawność kodu weryfikacyjnego
        signInWithPhoneAuthCredential(credential);
    }
}
