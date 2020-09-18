package com.mydreamworld.mech_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    Switch mySwitch;
    Button logout,allnews,hotnews,Unread,Likes,twod,CAD,CAM,Robotics,Scanners,am,IOT,Cloud,webinar,ARVR;
    TextView Topics;
    OptionalPendingResult<GoogleSignInResult> opr;
    ScrollView scrollable;
    TextView categories;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.logout:

                if(opr.isDone()){
                    GoogleSignInResult result=opr.get();
                    logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                                @Override
                                public void onResult(@NonNull Status status) {
                                    if (status.isSuccess()){
                                        Toast.makeText(MainActivity.this, "signout successfull.", Toast.LENGTH_SHORT).show();
                                        gotoMainActivity();
                                    }else{
                                        Toast.makeText(MainActivity.this, "signout failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                }else{

                    FirebaseAuth.getInstance().signOut();
                    gotoMainActivity();
                    Toast.makeText(MainActivity.this, "Logout successfull!..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Splash.class));
                    finish();
                }

            case R.id.feedback:
                Log.i("menu item selected","feedback");

                return true;
            case R.id.help:
                Log.i("menu item selected","help");
                return true;
            default:
                return false;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        logout=(Button) findViewById(R.id.logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        opr=Auth.GoogleSignInApi.silentSignIn(googleApiClient);

    }
    private void gotoMainActivity() {
        startActivity(new Intent(MainActivity.this,Splash.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}