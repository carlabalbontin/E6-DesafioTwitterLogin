package com.cbalt.desafiotwitterlogin;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            Toast.makeText(this, "You are signed in", Toast.LENGTH_SHORT).show();
        } else {
            // not signed in
            Toast.makeText(this, "You are not signed in", Toast.LENGTH_SHORT).show();
            signIn();
        }
    }

    private void signIn(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.TwitterBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Successfully signed in
        if (resultCode == RESULT_OK) {
            showToast("Resultó!");
        }

        // Sign in cancelled
        if (resultCode == RESULT_CANCELED) {
            showToast("Cancelado");
            return;
        }

        // No network
        /*if (resultCode == RESULT_NO_NETWORK) {
            showToast(R.string.no_internet_connection);
            return;
        }*/
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
