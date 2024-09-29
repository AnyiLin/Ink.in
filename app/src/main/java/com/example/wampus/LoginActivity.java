package com.example.wampus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wampus.databinding.ActivityLoginBinding;
import com.example.wampus.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

//public class LoginActivity extends AppCompatActivity {
//    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;
//    ImageView loginButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        loginButton = findViewById(R.id.buttonLogin);
//
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
////                .requestIdToken("1074085748857-28sv60jnnf6uk6ba2s91e00dpenrohct.apps.googleusercontent.com")
//                .requestEmail().build();
//        gsc = GoogleSignIn.getClient(this, gso);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });
//    }
//
//    void signIn() {
//        Intent signInIntent = gsc.getSignInIntent();
//        startActivityForResult(signInIntent, 1000);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                task.getResult(ApiException.class);
//                navigateToSecondActivity();
//            } catch (ApiException e) {
//                Toast.makeText(getApplicationContext(), "Something went wrong: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }
//
//    private void navigateToSecondActivity() {
//        finish();
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//    }
//
//}

//public class LoginActivity extends AppCompatActivity {
//    EditText username;
//    EditText password;
//    ImageButton loginButton;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        username = findViewById(R.id.username);
//        password = findViewById(R.id.password);
//        loginButton = findViewById(R.id.buttonLogin);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//
//                // have button take user to home page
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}


public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    ImageButton loginButton;

    // view binding
    private ActivityLoginBinding binding;
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "GOOGLE_SIGN_IN_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        // configure google sign in
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        googleSignInClient.revokeAccess();

        setContentView(binding.getRoot());


        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick: begin google sign in");
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google Signin intent result");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // successful sign in, now auth with firebase
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogleAccount(account);

            } catch (Exception e) {
                // failed sign in
                Log.d(TAG, "onActivityResult: " + e.getMessage());
            }
        }
    }

    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // login success
                        Log.d(TAG, "onSuccess: Logged in");

                        // get logged in user
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        //get user info
                        String uid = firebaseUser.getUid();
                        String email = firebaseUser.getEmail();

                        Log.d(TAG, "onSuccess: Email: " + email);
                        Log.d(TAG, "onSuccess: UID: " + uid);

                        // check if user is new or existing
                        if (authResult.getAdditionalUserInfo().isNewUser()) {
                            // user is new - Account created
                            Log.d(TAG, "onSuccess: Account Created...\n" + email);
                            Toast.makeText(LoginActivity.this, "Account Created...\n" + email, Toast.LENGTH_SHORT).show();
                        } else {
                            // existing user - Logged in
                            Log.d(TAG, "onSuccess: Existing user...\n" + email);
                            Toast.makeText(LoginActivity.this, "Existing user...\n" + email, Toast.LENGTH_SHORT).show();
                        }

                        // start profile activity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // login failed
                        Log.d(TAG, "onFailure: Loggin failed" + e.getMessage());
                    }
                });
    }
}