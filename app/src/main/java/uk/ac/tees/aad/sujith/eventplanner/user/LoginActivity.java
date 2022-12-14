package uk.ac.tees.aad.sujith.eventplanner.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.tees.aad.sujith.eventplanner.MainActivity;
import uk.ac.tees.aad.sujith.eventplanner.R;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout emailLoginHolder, passwordLoginHolder;
    private ProgressBar progressbar;
    private FirebaseAuth authentication;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        authentication = FirebaseAuth.getInstance();
        emailLoginHolder = findViewById(R.id.emailLoginHolder);
        passwordLoginHolder = findViewById(R.id.passwordLoginHolder);
        Button login = findViewById(R.id.login);
        TextView ltoS = findViewById(R.id.loginToSignup);
        TextView forgotPassword = findViewById(R.id.forgotPassword);
        progressbar = findViewById(R.id.progressbarLogin);

        login.setOnClickListener(view -> {
            passwordLoginHolder.getEditText().onEditorAction(EditorInfo.IME_ACTION_DONE);
            String email = emailLoginHolder.getEditText().getText().toString().trim();
            String password = passwordLoginHolder.getEditText().getText().toString().trim();

            if (email.isEmpty()) {
                emailLoginHolder.setError("Email is required!");
                emailLoginHolder.requestFocus();
                return;
            } else {
                emailLoginHolder.setError(null);
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLoginHolder.setError("Please enter a valid email!");
                emailLoginHolder.requestFocus();
                return;
            } else {
                emailLoginHolder.setError(null);
            }

            if (password.isEmpty()) {
                passwordLoginHolder.setError("Password is required!");
                passwordLoginHolder.requestFocus();
                return;
            } else {
                passwordLoginHolder.setError(null);
            }

            if (password.length() < 6) {
                passwordLoginHolder.setError("Password should consist a minimum of 6 characters!");
                passwordLoginHolder.requestFocus();
                return;
            } else {
                passwordLoginHolder.setError(null);
            }

            progressbar.setVisibility(View.VISIBLE);

            authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Please try again later.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Check credentials.", Toast.LENGTH_LONG).show();
                }
                progressbar.setVisibility(View.GONE);
            });
        });

        forgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));

        ltoS.setOnClickListener(view1 -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        authStateListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null) {
                if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    LoginActivity.this.startActivity(i);
                    LoginActivity.this.overridePendingTransition(0, 0);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        authentication.addAuthStateListener(authStateListener);
    }
}