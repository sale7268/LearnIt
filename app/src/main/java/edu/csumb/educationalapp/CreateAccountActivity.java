package edu.csumb.educationalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccountActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPasswordSignup);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
    }

    public void Signup(View view) {
        if(TextUtils.isEmpty((etName.getText()))) {
            etName.setError("Name is required");
        } else if(TextUtils.isEmpty((etEmail.getText()))) {
            etEmail.setError("Email is required");
        } else if(TextUtils.isEmpty((etPassword.getText()))) {
            etPassword.setError("Password is required");
        } else if(TextUtils.isEmpty((etConfirmPassword.getText()))) {
            etConfirmPassword.setError("Name is required");
        } else if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            Toast.makeText(CreateAccountActivity.this, "Password doesn't match!", Toast.LENGTH_LONG).show();
        } else {
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading...");
            progress.show();
            ParseUser user = new ParseUser();
            user.setUsername(etEmail.getText().toString().trim());
            user.setEmail(etEmail.getText().toString().trim());
            user.setPassword(etConfirmPassword.getText().toString());
            user.put("name", etName.getText().toString().trim());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    progress.dismiss();
                    if (e == null) {
                        Toast.makeText(CreateAccountActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(CreateAccountActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}