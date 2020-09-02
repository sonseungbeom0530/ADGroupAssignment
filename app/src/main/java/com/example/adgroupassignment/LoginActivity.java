package com.example.adgroupassignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    //Declares an instance of FirebaseAuth.
    private FirebaseAuth mAuth;
    private SignInButton googleSignInBtn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_SIGN_GOOGLE = 100; // request code for google login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Log.e("mAuth: ",mAuth.toString());

        setContentView(R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(onClickListener);
        findViewById(R.id.btnReg).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPWResetBtn).setOnClickListener(onClickListener);
//        findViewById(R.id.googleSignInBtn).setOnClickListener(onClickListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    login();
                    break;
                case R.id.btnReg:
                    myStartActivity(UserRegisterActivity.class);
                    break;
//                case R.id.gotoPWResetBtn:
//                    myStartActivity(PasswordResetActivity.class);
//                    break;
            }
        }
    };

    private void login() {
        // get email and pw
        String email = ((EditText)findViewById(R.id.etEmail)).getText().toString();
        String pw = ((EditText)findViewById(R.id.etPass)).getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = firebaseDatabase.getReference();

        final DatabaseReference userID = rootRef.child("Users").child(mAuth.getUid()); // 유저 아이디 밑
        Log.e("asdfasdf",mAuth.getUid());
        Log.e("asdfasdf",userID.child("accountType").toString());



//        userID.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String data = dataSnapshot.getValue(String.class);
//                Log.e("asdfasdf",data);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        if (email.length() > 0 && pw.length() > 0){
//            final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout);
//            loaderLayout.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            loaderLayout.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                userID.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        // Get user value
                                        String data = dataSnapshot.child("accountType").getValue(String.class);
//                                        Log.e("asdfasdf",data);

                                        if(data.equals("User")){
                                            myStartActivity(UserActivity.class);
                                            Toast.makeText(LoginActivity.this, data + " login success", Toast.LENGTH_SHORT).show();
                                        }else if(data.equals("Admin")){
                                            myStartActivity(AdminActivity.class);
                                            Toast.makeText(LoginActivity.this, data + " login success", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                });

                            } else {
                                // If login fails, display a message to the user.
                                if (task.getException() != null){
                                    Toast.makeText(LoginActivity.this,"Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }else{
            Log.e("mAuth3: ",mAuth.toString());
            Toast.makeText(LoginActivity.this,"Please enter email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//    }
}