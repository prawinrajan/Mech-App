package com.mydreamworld.mech_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    Button regsiter;
    TextInputLayout password,email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email=(TextInputLayout) findViewById(R.id.uname);
        password=(TextInputLayout) findViewById(R.id.pwd);

        regsiter=(Button) findViewById(R.id.register);

        auth=FirebaseAuth.getInstance();
    }
    public void regsiter(View v){
        String emailid=email.getEditText().getText().toString();
        String pwd=password.getEditText().getText().toString();

        if(TextUtils.isEmpty(emailid)||TextUtils.isEmpty(pwd) ){
            Toast.makeText(Register.this,"empty user name or password",Toast.LENGTH_SHORT).show();
        }else if(pwd.length()<6){
            Toast.makeText(Register.this,"Password too short",Toast.LENGTH_SHORT).show();
        }else{
            registerUser(emailid,pwd);
        }

    }

    private void registerUser(String emailid, String pwd) {
        try{
            Log.i("values",emailid);
            Log.i("values",pwd);

            auth.createUserWithEmailAndPassword(emailid,pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d("registerc", "createUserWithEmail:success");
                        startActivity(new Intent(Register.this,MainActivity.class));
                        Toast.makeText(Register.this," Registeration completed.",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(Register.this,"Registeration failed.",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error",e.getMessage());
        }
    }
}