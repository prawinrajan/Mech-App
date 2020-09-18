package com.mydreamworld.mech_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button login;
    TextInputLayout password,email;
    private FirebaseAuth auth;



    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button) findViewById(R.id.login);

        email=(TextInputLayout) findViewById(R.id.uname);
        password=(TextInputLayout) findViewById(R.id.pwd);
        FirebaseApp.initializeApp(this);
        auth=FirebaseAuth.getInstance();
    }
    public void login(View v){
        String emailtext=email.getEditText().getText().toString();
        String pwdtext=password.getEditText().getText().toString();
        if(TextUtils.isEmpty(emailtext)||TextUtils.isEmpty(pwdtext) ){
            Toast.makeText(Login.this,"email id and password is none. enter email id & password.",Toast.LENGTH_SHORT).show();
        }
        loginUser(emailtext,pwdtext);
    }

    private void loginUser(String emailtext, String pwdtext) {
        auth.signInWithEmailAndPassword(emailtext,pwdtext).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this,"Login sucessfull!.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,MainActivity.class));
                finish();
            }
        });
    }
}