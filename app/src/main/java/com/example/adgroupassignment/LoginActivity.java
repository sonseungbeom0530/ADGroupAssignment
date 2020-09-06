package com.example.adgroupassignment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    //Declares an instance of FirebaseAuth.
    private FirebaseAuth mAuth;
    private SignInButton googleSignInBtn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_SIGN_GOOGLE = 100; // request code for google login
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Log.e("mAuth: ",mAuth.toString());

        setContentView(R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(onClickListener);
        findViewById(R.id.tvReg).setOnClickListener(onClickListener);
        findViewById(R.id.tvFindPassword).setOnClickListener(onClickListener);
//        findViewById(R.id.googleSignInBtn).setOnClickListener(onClickListener);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

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
                case R.id.tvReg:
                    myStartActivity(UserRegisterActivity.class);
                    break;
                case R.id.tvFindPassword:
                    showRecoverPasswordDialog();
//                    myStartActivity(PasswordResetActivity.class);
//                    break;
            }
        }
    };

    private void showRecoverPasswordDialog() {
        //AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");

        //set layout linear layout
        LinearLayout linearLayout = new LinearLayout(this);
        //views to set in dialog
        final EditText emailEt = new EditText(this);
        emailEt.setHint("Email");
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEt.setMinEms(16);

        linearLayout.addView(emailEt);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        //buttons recover
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //input email
                String email=emailEt.getText().toString().trim();
                beginRecovery(email);
            }
        });
        //buttons cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss();
            }
        });

        //show dialog
        builder.create().show();
    }

    private void beginRecovery(String email) {
        //show progress dialog
        progressDialog.setMessage("Sending email...");
        progressDialog.show();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Email sent",Toast.LENGTH_SHORT).show();
                        }
                        else {
                           //Toast.makeText(LoginActivity.this, "Failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        //get and show proper error message
                        Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

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
                                            myStartActivity(MainActivity.class);
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