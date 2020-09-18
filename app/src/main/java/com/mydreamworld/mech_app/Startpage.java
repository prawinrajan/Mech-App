package com.mydreamworld.mech_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Startpage extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener{
    Button regiser;
    Button login;
    SignInButton signInButton;
    private  GoogleApiClient googleApiClient;
    private static final int SIGN_IN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        regiser=(Button) findViewById(R.id.regsiter);
        login=(Button) findViewById(R.id.login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        signInButton=findViewById(R.id.signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN);
            }
        });
    }
    public void login(View v) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish(); //does not return to this page
    }
    public void regsiter(View v) {
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }else{
                Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}